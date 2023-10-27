import os
import time
import threading
import matplotlib.pyplot as plt
from datetime import datetime
from numpy import sort
from numpy.random import randint
from unidecode import unidecode

N_SAMPLES = 8 #100_000_000

class Sample:
    def __init__(self, name, title, transformFunc = lambda a: a):
        self.name = name
        self.title = title 
        self.times = []
        self.elements = []
        self.transformFunc = transformFunc

    def add_elements(self, elements):
        self.elements.append(elements)

    def add_times(self, time):
        self.times.append(time)

    def _filename(self, dir, ext):
        d = f"{dir}/" if dir is not None else ""
        return f"{d}{unidecode(self.name)}.{ext}"

    def save_graph(self, dir):
        _, ax = plt.subplots()
        ax.set_xlabel('elementos')
        ax.set_ylabel('tempo (s)')
        ax.set_title(self.title)
        ax.plot(self.elements, self.times)
        ax.set_xscale('log')

        fname = self._filename(dir, 'png')
        print(f"{fname} salvo")
        plt.savefig(fname)

    def save_times(self, dir):
        fname = self._filename(dir, 'txt')
        f = open(fname, "a")
        for i in range(0, len(self.elements)):
            padding = (15 - len(str(self.elements[i]))) * '.'
            f.write(f"{self.elements[i]}{padding}{self.times[i]}s\n")
        f.close()
        print(f"{fname} salvo")

    def print_table(self):
        for i in range(0, len(self.elements)):
            padding = (15 - len(str(self.elements[i]))) * '.'
            print(f"{self.elements[i]}{padding}{self.times[i]}s")


def heapify(arr, n, i):
    largest = i
    l = 2 * i + 1
    random = 2 * i + 2

    if l < n and arr[i] < arr[l]:
        largest = l

    if random < n and arr[largest] < arr[random]:
        largest = random

    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        heapify(arr, n, largest)

def heapsort(arr):
    n = len(arr)

    for i in range(n//2, -1, -1):
        heapify(arr, n, i)

    for i in range(n-1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]
        heapify(arr, i, 0)

def measure_heapsort(arr):
    start = time.perf_counter()
    heapsort(arr)
    end = time.perf_counter()

    return len(arr), end-start

def arr_thread(sample, arr):
    def print_label(sample, status):
        padding = (15 - len(sample.name)) * "."
        print(f"{datetime.now().strftime('%H:%M:%S')} {sample.name}{padding}{len(a)} elementos\t{status}") 

    a = sample.transformFunc(arr)
 
    print_label(sample, "Start Thread")
    elements, times = measure_heapsort(a)
    sample.add_elements(elements)
    sample.add_times(times)
    print_label(sample, "Done Thread")

def n_samples_gen(n):
    return 10**n

samples = [
        Sample("aleatório","Complexidade Heapsort - Aleatório", lambda arr: arr), 
        Sample("ordenado", "Complexidade Heapsort - Ordenado", lambda arr: sort(arr)),
        Sample("invertido", "Complexidade Heapsort - Invertido", lambda arr: arr[::-1])
        ]

print(f"Limite: {n_samples_gen(N_SAMPLES)} ")

for i in range(1, N_SAMPLES + 1):
    size = n_samples_gen(i)
    arr = randint(0, 1000 * i, size)

    arr_threads = []
    for sample in samples:
        thread = threading.Thread(target=arr_thread, args=(sample, arr))
        arr_threads.append(thread)

    for thread in arr_threads:
        thread.start()

    for thread in arr_threads:
        thread.join()

for sample in samples:
    print(f"\n{sample.name}")
    sample.print_table()

now = datetime.now().strftime('%H%M%S_%d%m%y')
out_dir = f"hs_output_{n_samples_gen(N_SAMPLES)}_{now}"
os.makedirs(out_dir)

print("\nsalvando gráficos e tempos")
for sample in samples:
    sample.save_graph(out_dir)
    sample.save_times(out_dir)

