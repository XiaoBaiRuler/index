function init(flag){
    // 博客文本内容
    app.html = document.getElementById('html').value;
    document.getElementById('content').innerHTML = marked(app.html
        , {
            baseUrl: null,
            breaks: true,
            gfm: true,
            headerIds: true,
            headerPrefix: '',
            highlight: function(code, language){
                const validLanguage = hljs.getLanguage(language) ? language : 'plaintext';
                return hljs.highlight(validLanguage, code).value;
            },
            langPrefix: 'language-',
            mangle: true,
            pedantic: false,
            renderer: new marked.Renderer(),
            silent: false,
            smartLists: true,
            smartypants: true,
            xhtml: true

        });
    hljs.highlightAll()

    if (!flag){
        // 模态框导图
        let options = {
            mode: 'full',
            container: 'mapContainer',
            editable: true,
            theme:'orange',
            support_html: true,
            view:{
                engine: 'canvas',   // 思维导图各节点之间线条的绘制引擎
                hmargin:100,        // 思维导图距容器外框的最小水平距离
                vmargin:200,         // 思维导图距容器外框的最小垂直距离
                line_width:3,       // 思维导图线条的粗细
                line_color:'#555'   // 思维导图线条的颜色
            },
            layout:{
                hspace:150,          // 节点之间的水平间距
                vspace:200,          // 节点之间的垂直间距
                pspace:2           // 节点与连接线之间的水平间距（用于容纳节点收缩/展开控制器）
            },
        }
        app.jm = new jsMind(options)
    }
}

function toShowBlog(nodeId){
    axios.get("/public/getNodeById?id=" + nodeId)
        .then(response => {
            app.showNode = response.data
            if (app.showNode.content === null){
                app.showNode.content = "空内容";
            }
            document.getElementById('showBlogContent').innerHTML = marked(app.showNode.content
                , {
                    baseUrl: null,
                    breaks: true,
                    gfm: true,
                    headerIds: true,
                    headerPrefix: '',
                    highlight: function(code, language){
                        const validLanguage = hljs.getLanguage(language) ? language : 'plaintext';
                        return hljs.highlight(validLanguage, code).value;
                    },
                    langPrefix: 'language-',
                    mangle: true,
                    pedantic: false,
                    renderer: new marked.Renderer(),
                    silent: false,
                    smartLists: true,
                    smartypants: true,
                    xhtml: true
                });
            hljs.highlightAll()
            $('.ui.showBlog.fullscreen.modal').modal('show');
        })
        .catch(error => console.log(error))
}

function toShowAdminBlog(nodeId){
    axios.get("/private/getNode?nodeId=" + nodeId)
        .then(response => {
            if (response.data === ""){
                alert("节点以删除");
                return;
            }
            app.showNode = response.data
            if (app.showNode.content === null){
                app.showNode.content = "空内容";
            }
            document.getElementById('showBlogContent').innerHTML = marked(app.showNode.content
                , {
                    baseUrl: null,
                    breaks: true,
                    gfm: true,
                    headerIds: true,
                    headerPrefix: '',
                    highlight: function(code, language){
                        const validLanguage = hljs.getLanguage(language) ? language : 'plaintext';
                        return hljs.highlight(validLanguage, code).value;
                    },
                    langPrefix: 'language-',
                    mangle: true,
                    pedantic: false,
                    renderer: new marked.Renderer(),
                    silent: false,
                    smartLists: true,
                    smartypants: true,
                    xhtml: true
                });
            hljs.highlightAll()
            $('.ui.showBlog.fullscreen.modal').modal('hide others').modal('show')
        })
        .catch(error => console.log(error))
}

function toShowMap(nodeId){
    axios.get("/public/getMind?nodeId=" + nodeId)
        .then(response => {
            let m = response.data
            let res = []
            m.forEach((item) =>{
                let obj = {}
                obj.id = item.id
                obj.parentid = item.parentid
                obj.isroot = item.isroot
                obj.topic = item.topic
                obj.direction = item.direction
                obj.expanded = item.expanded
                res.push(obj)
            })
            let mind = {
                "meta":{
                    "name":"测试",
                    "author":"xiaobai",
                    "version":"1.0",
                },
                "format":"node_array",
                "data": res
            }
            app.jm.show(mind)
            $('.ui.showMap.fullscreen.modal').modal('show');
        })
        .catch(error => console.log(error))
}

function toShowSug(suggestId){
    axios.get("/person/public/getSuggest?suggestId=" + suggestId)
        .then(response => {
            app.suggest = response.data
            $('.ui.showSuggest.fullscreen.modal').modal('show');
        })
        .catch(error => console.log(error))
}