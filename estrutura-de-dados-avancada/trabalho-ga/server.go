package main

import (
	"fmt"
	"log"
	"net"
	"net/http"
	"text/template"
	"time"
)

var port string

func index(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "text/html")
	layout := `<!DOCTYPE html>
    <html>
        <body>
            <h1>My First Heading</h1>
        </body>
    </html>`
	mpl := template.Must(template.New("tpl").Parse(layout))
	err := mpl.Execute(w, tree)
	if err != nil {
		log.Fatal(err)
	}
}

func initHttp() {
	port = "3333"
	conn, _ := net.DialTimeout("tcp", net.JoinHostPort("", port), time.Millisecond*100)
	if conn != nil {
		return
	}
	http.HandleFunc("/", index)
	_ = http.ListenAndServe(fmt.Sprintf(":%s", port), nil)
}
