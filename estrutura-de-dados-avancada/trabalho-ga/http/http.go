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
		return ""
	}

	builder := strings.Builder{}
	if n.Left != nil || n.Right != nil {
		if n.Left != nil {
			builder.WriteString(toHtml(n.Left))
		}
		if n.Right != nil {
			builder.WriteString(toHtml(n.Right))
		}
	}
	if n.Left == nil && n.Right == nil {
		return fmt.Sprintf(`
        <li><div>%d</div></li>`, n.Value)
	}

	return fmt.Sprintf(`
    <ul>
        <li><div>%d</div>
            <ul>
                %s
            </ul>
        </li>
    </ul>`, n.Value, builder.String())
}

func index(w http.ResponseWriter, r *http.Request) {
	layout := fmt.Sprintf(`
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8" />
            <link href="/styles/tree.css" type="text/css" rel="stylesheet">
        <head>
        <body>
            <h1>Arvore AVL</h1>
            <div class="tree">
                %s
            </div>
        </body>
    </html>`, toHtml(avl.Tree))

	w.Header().Set("Content-Type", "text/html")
	mpl := template.Must(template.New("tpl").Parse(layout))
	err := mpl.Execute(w, avl.Tree)
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
