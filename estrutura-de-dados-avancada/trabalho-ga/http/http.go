package http

import (
	"fmt"
	"log"
	"net"
	"net/http"
	"strings"
	"text/template"
	"time"

	"github.com/gabrielmusskopf/avl"
)

var port string

func toHtml(n *avl.TreeNode) string {
	if n == nil {
		return "<li><div></div></li>"
	}

	builder := strings.Builder{}
	if n.Left == nil && n.Right == nil {
		return fmt.Sprintf(`<li><div>%d</div></li>`, n.Value)
	}
	builder.WriteString(toHtml(n.Left))
	builder.WriteString(toHtml(n.Right))

	return fmt.Sprintf(`
    <ul>
        <li><div>%d</div>
            <ul>%s</ul>
        </li>
    </ul>`, n.Value, builder.String())
}

func index(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "text/html")
	tmpl := template.Must(template.ParseFiles("http/tree.html"))
	err := tmpl.Execute(w, toHtml(avl.Tree))
	if err != nil {
		log.Fatal(err)
	}
}

func InitHttp() {
	port = "3333"
	conn, _ := net.DialTimeout("tcp", net.JoinHostPort("", port), time.Millisecond*100)
	if conn != nil {
		return
	}
	http.Handle("/styles/", http.StripPrefix("/styles/", http.FileServer(http.Dir("http/styles"))))
	http.HandleFunc("/", index)

	_ = http.ListenAndServe(fmt.Sprintf(":%s", port), nil)
}
