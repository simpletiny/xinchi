<%@ page language="java" pageEncoding="UTF-8"%>
 <%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<jsp:include page="layout.jsp" />
<title>欣驰国际</title>
<script src="<%=basePath %>static/vendor/chart/chart.js"></script>
<script>
    options = {

        //Boolean - If we show the scale above the chart data
        scaleOverlay : true,

        //Boolean - If we want to override with a hard coded scale
        scaleOverride : false,

        //** Required if scaleOverride is true **
        //Number - The number of steps in a hard coded scale
        scaleSteps : null,
        //Number - The value jump in the hard coded scale
        scaleStepWidth : null,
        //Number - The scale starting value
        scaleStartValue : null,

        //String - Colour of the scale line
        scaleLineColor : "rgba(0,0,0,.1)",

        //Number - Pixel width of the scale line
        scaleLineWidth : 1,

        //Boolean - Whether to show labels on the scale
        scaleShowLabels : true,

        //Interpolated JS string - can access value
        scaleLabel : "111",

        //String - Scale label font declaration for the scale label
        scaleFontFamily : "'Arial'",

        //Number - Scale label font size in pixels
        scaleFontSize : 12,

        //String - Scale label font weight style
        scaleFontStyle : "normal",

        //String - Scale label font colour
        scaleFontColor : "#666",

        ///Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - Whether the line is curved between points
        bezierCurve : true,

        //Boolean - Whether to show a dot for each point
        pointDot : true,

        //Number - Radius of each point dot in pixels
        pointDotRadius : 3,

        //Number - Pixel width of point dot stroke
        pointDotStrokeWidth : 1,

        //Boolean - Whether to show a stroke for datasets
        datasetStroke : true,

        //Number - Pixel width of dataset stroke
        datasetStrokeWidth : 2,

        //Boolean - Whether to fill the dataset with a colour
        datasetFill : true,

        //Boolean - Whether to animate the chart
        animation : true,

        //Number - Number of animation steps
        animationSteps : 60,

        //String - Animation easing effect
        animationEasing : "easeOutQuart",

        //Function - Fires when the animation is complete
        onAnimationComplete : null

    }

    var data = {
        labels : ["2015-01-01","2015-01-02","2015-01-03","2015-01-04","2015-01-05","2015-01-06","2015-01-07"],
        datasets : [
            {
                fillColor : "rgba(220,220,220,0.5)",
                strokeColor : "rgba(220,220,220,1)",
                pointColor : "rgba(220,220,220,1)",
                pointStrokeColor : "#fff",
                data : [65,59,90,81,56,55,40]
            },
            {
                fillColor : "rgba(151,187,205,0.5)",
                strokeColor : "rgba(151,187,205,1)",
                pointColor : "rgba(151,187,205,1)",
                pointStrokeColor : "#fff",
                data : [28,48,40,19,96,27,100]
            }
        ]
    };

    var ctx = $("#userChart").get(0).getContext("2d");
    var userChart = new Chart(ctx).Line(data, options);
   </script>
</head>
<body>

  <div class="main-body">


    <div class="main-container">
        <canvas id="userChart" width="1000" height="400"></canvas>
    </div>
  </div>





 </body>
</html>