
with open("grafo-grande.txt", "w") as f:
    f.write("1000,2000\n")
    for i in range(999):
        f.write(f"{i},{i+1}\n")
    for i in range(1000, 1999):
        u = i % 1000
        v = (i * 3) % 1000
        if u != v:
            f.write(f"{u},{v}\n")
    f.write("0,999\n")
    f.write("0,999\n")
