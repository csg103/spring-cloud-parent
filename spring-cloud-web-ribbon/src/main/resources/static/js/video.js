/**
 * Created by csg on 2017/7/12.
 */
function startPage() {


    $(".activeSelect").click(
        function () {
            var num = $(".sumTv").attr("value");
            //获得当前选中菜单的NUMID属性，对应下面的div顺序
            var select = $(this).attr("numId");
            for (var i = 0; i <= num; i++) {
                $(".activeSelect").eq(i).removeClass("active item activeSelect").addClass("item activeSelect");
                $(".hiddenTv").eq(i).removeClass("hiddenTv").addClass("hidden hiddenTv");
            }
            $(this).removeClass("item activeSelect").addClass("active item activeSelect");
            $(".hiddenTv").eq(select).removeClass("hidden hiddenTv").addClass("hiddenTv");
        }
    )
    /* "刷新" 功能
     * 获取单个列表全部数据，采用隐藏域对不需要展示的数据进行隐藏。
     * 当刷新时，把当前展示数据获取存放到变量，然后把隐藏于前n条赋值给当前展示数据框
     * 最后把存放在变量中的数据追加到隐藏域末尾
     * */
    $(".num").click(
        function () {
            var size = $(this).attr("value");
            for (var i = 0; i < size; i++) {
                var videoBox = $(this).parent().find(".videoItem");
                var imageValue = videoBox.eq(i).find(".videoImage").eq(0).attr("src");
                var titleValue = videoBox.eq(i).find(".videoTitle").eq(0).text();
                var urlValue = videoBox.eq(i).find(".videoUrl").eq(0).attr("href");
                var node = videoBox.eq(size);
                var image = node.find(".videoImage").eq(0).attr("value");
                var title = node.find(".videoTitle").eq(0).attr("value");
                var url = node.find(".videoUrl").eq(0).attr("href");
                videoBox.eq(i).find(".videoImage").eq(0).attr("src", image);
                videoBox.eq(i).find(".videoTitle").eq(0).text(title);
                videoBox.eq(i).find(".videoUrl").eq(0).attr("href", url);
                node.remove();
                var divBox = $("<div class=\"videoItem\"></div>");
                var hiddenNode = $("<div class=\"hidden\"  ></div>");
                var imageNode = $("<p class=\"videoImage\" ></p>");
                var titleNode = $("<p class=\"videoTitle\" ></p>");
                var urlNode = $("<a href='' class=\"videoUrl\"></a>");
                imageNode.attr("value", imageValue);
                titleNode.attr("value", titleValue);
                urlNode.attr("href", urlValue);
                hiddenNode.append(imageNode);
                hiddenNode.append(titleNode);
                hiddenNode.append(urlNode);
                divBox.append(hiddenNode);
                $(this).parent().find(".add").append(divBox);
            }
        }
    );
    /* 获取友情链接信息 */
    $(document).ready(
        function () {
            $.ajax({
                url: "https://api.map.baidu.com/location/ip?ak=Bnri1fKeSDqQ9z5G9ckvfGue&coor=bd09ll",
                type: "get",
                dataType: "jsonp",
                data: "",
                crossDomain: true,
                headers: {'Content-Type': 'application/json'},
                success: function (res) {
                    getWeather(res.content.point.x, res.content.point.y);

                },
                error: function (XMLHttpRequest, textStatus) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            })
        }
    );
}
//根据经纬度查询天气
function getWeather(x, y) {
    $.ajax({
        url: "api/getWeather",
        type: "get",
        dataType: "json",
        data: {"x": x, "y": y},
        async: false,
        headers: {'Content-Type': 'application/json'},
        success: function (res) {
            $("#temperature").html(res[0].temperature);
            $("#time").html(res[0].time);
        },
        error: function (XMLHttpRequest, textStatus) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    })
}