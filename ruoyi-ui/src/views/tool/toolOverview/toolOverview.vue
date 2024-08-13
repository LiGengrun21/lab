<template>
    <div class="page">
        <div class="up">
            <!--设备使用情况视图-->
            <div class="box1">
                <div class=" Equipment">
                    <h2>设备使用情况</h2>
                    <p>{{equipment_use}}</p>
                    <li class="el-icon-set-up"></li>
                </div>
                <!--异常情况查看-->
                <div class="equiment_detail">
                    <div class="item1" @click="chnage_detail()">
                        <h3>使用情况</h3>
                        <p>{{using_normal}}</p>
                        <li class="el-icon-s-promotion"></li>
                    </div>
                    <div class="item2">
                        <h3>异常项</h3>
                        <p>{{using_abnormol}}</p>
                        <li class="el-icon-warning-outline"></li>
                    </div>
                    <div class="item3">
                         <el-button type="primary">使用详情</el-button>
                         <el-button type="primary">预约详情</el-button>
                         <el-button type="warning">异常查看</el-button>
                    </div>
                </div>
            </div>
            <!--异常情况查看-->
            <div class="equment_error">
                <div class="error_view">
                    <h2>设备报修设备</h2>
                    <p>{{error}}</p>
                    <li class="el-icon-set-up"></li>
                </div>
                <div class="error_details">
                    <div class="item1">
                        <h4>原因一</h4>
                        <p>2</p>
                        <li class="el-icon-position"></li>
                    </div>
                    <div class="item2">
                        <h4>原因二</h4>
                        <p>2</p>
                        <li class="el-icon-share"></li>
                    </div>
                    <div class="item3">
                        <h4>原因三</h4>
                        <p>3</p>
                        <li class="el-icon-full-screen"></li>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="down">
            <div class="echarts1" id="month_echart" >
            </div>
            <div class="bar2" id="container" >
            </div>
            
            
        </div>
    </div>
</template>
<script>
let echarts = require('echarts/lib/echarts');
// 引入柱状图
require('echarts/lib/chart/bar');
// 引入柱状图
require('echarts/lib/chart/pie');
require("echarts/lib/chart/line")
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');

export default {
    data(){
        return{
            equipment_use:"88/103",
            using_normal:86,
            using_abnormol:2,
            error:"15 / 80",
            error_list:[{num:5},{num:5},{num:5}],
            list:{
                value:[1,2,3,4,5,6,7],
                label:["1","2","3","4","5","6","7"]
            },
        }
    },
    mounted(){
        this.drawBar();
        this.draw_line()

    },
    methods:{
        drawBar(){
        // 基于dom，初始化echarts实例

     let data=[];
           for(var i=0;i<30;i++){
             data.push(Math.floor(Math.random()*1000+20))
           }

           let option = {
               title : {
                   text: '月设备使用情况',
               },
               tooltip : {
                   trigger: 'axis',
                   backgroundColor:' rgba(23, 99, 143, 0.75)' ,
                   padding:[5,10],
                   formatter(params){
                    let point=params[0];
                    return '2021年'+point.name+'月 : '+point.value
                   }
               },
               legend: {
                   data:['2021'],
                   textStyle: {
                     color: '#7aaab8',
                     fontSize:14
                   },

               },

               calculable : true,

               xAxis : [
                   {
                     name : '(月)',
                     nameLocation: 'end',
                     nameTextStyle:{
                     color: '#292929',
                     fontSize:16,
                     padding:[50, 0, 0, 10]
                    },
                       type : 'category',
                       boundaryGap : false,
                       data : ['1','2','3','4','5','6','7','8','9','10','11','12',"13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","29","30"],
                       axisLine: {
                         show: false
                       },
                       axisTick: {
                           show: false
                       },
                       offset:10,
                       axisLabel: {
                           show:true,
                           textStyle: {
                             color: '#292929',
                             fontSize:16
                           }
                       },
                       axisPointer:{ //基准线
                         lineStyle:{
                          color:'rgb(46, 148, 245)'
                        }
                       }

                   }
               ],


               yAxis : [
                   {
                     axisLabel: {
                       show:true,
                       textStyle: {
                         color: '#292929',
                         fontSize:16
                       }
                    },
                    axisTick: {
                       show: false
                   },
                   axisLine: {
                       show: false
                   },
                   offset:10,
                   splitLine: {
                     show: true,
                     lineStyle:{
                         type:'dashed',
                         color: [' #292929b9']

                     }
                   }
                 }
               ],
               series : [
                   {
                       name:'2021',
                       type:'line',
                       smooth:true,
                       symbolSize: 10,   //设定实心点的大小
                       lineStyle:{
                         color:'#07bdf5' //改变折线颜色
                       },
                       lineWidth:2,
                       itemStyle: {
                         normal: {
                            areaStyle: {type: 'default'},
                            color: new echarts.graphic.LinearGradient(
                                 0, 0, 0, 1,
                                 [
                                     {offset: 0, color: '#1c61f5'},
                                     {offset: 0.5, color: '#1c93f5be'},
                                     {offset: 1, color: '#70d3eca6'}
                                 ]
                             ),
                            borderColor:'#1d9aee',//拐点边框颜色
                            borderWidth:2//拐点边框大小
                         }},
                        data:data
                   },

               ]
           };
           let myChart = echarts.init(document.getElementById('month_echart'));
           myChart.setOption(option);
      },
     draw_line(){
         var date=this.list
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var option = null;
			option = {
						 backgroundColor:"transparent",
						 tooltip: {
							 show: true,
							 formatter: "{b}:{c}"
							 },
					 grid: {
							 left: '5%',
							 top: '12%',
							 right: '1%',
							 bottom: '8%',
							 containLabel: true
							 },
					 
						 xAxis: {
								 type: 'category',
								 axisTick: {
									 show: false,
									 alignWithLabel: false,
									 length: 5,
		 
								 },
								 "splitLine": { //网格线
									 "show": false
								 },
								 inverse: 'true', //排序
								 axisLine: {
									 show: false,
									 lineStyle: {
										 color: '#fff',
									 }
								 },
								 data:date.label
							 },
						 yAxis: [{
							 type: 'value',
							 show:false,
							 position: 'top',
							 axisTick: {
								 show: false
							 },
							 axisLine: {
								 show: false,
								 lineStyle: {
									 color: '#fff',
								 }
							 },
							 splitLine: {
								 show: false
							 },
						 }],
						 series: [{
								 name: '能耗值',
								 type: 'bar',
								 label: {
									 normal: {
										 show: true,
										 position: 'top',
										 formatter: '{c}',
										 textStyle: {
											 color: 'white' //color of value
										 }
									 }
								 },
								 itemStyle: {
									 //通常情况下：
									 normal: {
										 barBorderRadius: 8,
										 //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
										 color: function (params) {
											 var colorList = [
												 ['#b250ff', 'rgba(11,42,84,1)'],
												 ['#ff9717', 'rgba(11,42,84,1)'],
												 ['#61dbe8', 'rgba(11,42,84,1)'],
												 ['#1ace4a', 'rgba(11,42,84, 1)'],
											 ];
											 var index = params.dataIndex;
											 if (params.dataIndex >= colorList.length) {
												 index = params.dataIndex - colorList.length;
											 }
											 return new echarts.graphic.LinearGradient(0, 0, 0, 1,
												 [{
														 offset: 0,
														 color: colorList[index][0]
													 },
													 {
														 offset: 1,
														 color: colorList[index][1]
													 }
												 ]);
										 },
									 },
								 },
								 barGap: '0%',
								 barCategoryGap: '50%',
								 data:date.value
							 }]
					 };           
		 
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
            }

        }
</script>
<style  scoped>
.up{
    display: flex;
}
    .box1{
        width: 600px;
        height:600px ;
        
    }
        .Equipment{
            height:280px;
            background-image: linear-gradient(#53DEE3, #70EEFA, #75F0FF);
            margin-left: 25px;
            margin-top: 25px;
            border-radius: 6px;
            box-shadow: 0px 0px 5px 5px #2929292c;
        }
          .Equipment  h2{
                margin-left: 10px;
                margin-top: 10px;
                font-size: 28px;
                font-weight: bold;
                color: white;
            }
            .Equipment p{
                text-align: center;
                font-size: 90px;
                margin-top:50px;
                color: white;
            }
           .Equipment li{
               position: absolute;
                font-size: 600%;
                margin-left: 400px;
                margin-top: -100px;
                color: white;
                box-shadow: 5px 5px 5px 0px #0000000a;
            }
            .equiment_detail{
                display: flex;
                flex: 1;
                width: 600px;

            }   
             .equiment_detail .item1{
                 width: 250px;
                 height: 150px;
                 margin-left: 25px;
                 background: linear-gradient(#58CD75,#58CD75,#57CD78);
                 border-radius: 6px;
                 margin-top: 10px;
             }
             .equiment_detail .item1:hover{
                background: linear-gradient(#53DEE3, #70EEFA, #75F0FF);
             }
                .equiment_detail .item1 h3{
                        margin-left: 5px;
                        margin-top: 5px;
                        color: white;
                        font-size: 25px;
                }
                .equiment_detail .item1 p{
                    color: white;
                    font-size: 50px;
                    margin-top: -25px;
                    text-align: center;
                }
                .equiment_detail .item1 li{
                    position: absolute;
                    color: white;
                    font-size: 60px;
                    margin-top: -60px;
                    margin-left: 120px;
                }
             .equiment_detail .item2{
                 width: 250px;
                 height: 150px;
                 margin-left: 20px;
                 background: linear-gradient(#FBAE54,#FCA034,#FEC578);
                 border-radius: 6px;
                 margin-top: 10px;
             }
             .equiment_detail .item2:hover{
                 background: linear-gradient(#53DEE3, #70EEFA, #75F0FF);
             }
                .equiment_detail .item2 h3{
                        margin-left: 5px;
                        margin-top: 5px;
                        color: white;
                        font-size: 25px;
                }
                .equiment_detail .item2 p{
                    color: white;
                    font-size: 50px;
                    margin-top: -25px;
                    text-align: center;
                }
                .equiment_detail .item2 li{
                    position: absolute;
                    color: white;
                    font-size: 60px;
                    margin-top: -60px;
                    margin-left: 120px;
                }
             .equiment_detail .item3{
                 width: 250px;
                 height: 150px;
                 display: flex;
                 flex-direction: column;
                 margin-right: auto;
                 
                 margin-left: 8px;
                 border-radius: 6px;
                 margin-top: 10px;
             }
             .equiment_detail .item3 .el-button{
                width: 90%;
                margin-top: 10px;
                margin-right: auto;
             }
             .equiment_detail .item3 .el-button:nth-child(1)
             {
                 margin-left: 10px;
             }
        .equment_error{
            width: 620px;
            margin-left: 30px;
           
        }
        .equment_error .error_view{
            margin-top: 25px;
            
           
            height:285px;
            background: linear-gradient(-130deg,#6EE7BE 30%,#77E7D7 ,#67E4E0);
            border-radius: 6px;
            box-shadow: 0px 0px 5px 5px #2929292c;
        }   
        .error_view h2{
            margin-left: 10px;
                margin-top: 10px;
                font-size: 28px;
                font-weight: bold;
                color: white;
        }
        .error_view p{
            text-align: center;
                font-size: 90px;
                margin-top:50px;
                color: white;
        }
        .error_view li{
             position: absolute;
                font-size: 600%;
                margin-left: 500px;
                margin-top: -100px;
                color: white;
                box-shadow: 5px 5px 5px 0px #0000000a;
        }
        .equment_error .error_details
        {
             margin-top:10px;
           
             display: flex;
        } 
         .equment_error .error_details .item1
         {
            
            width: 220px;
            height:150px;
            background: linear-gradient(#58CD75,#58CD75,#57CD78);
            border-radius: 6px;

         }
             .equment_error .error_details .item1:hover{
                background: linear-gradient(#53DEE3, #70EEFA, #75F0FF);
             }
                .equment_error .error_details .item1 h4{
                        margin-left: 5px;
                        margin-top: 5px;
                        color: white;
                        font-size: 25px;
                }
                .equment_error .error_details .item1 p{
                    color: white;
                    font-size: 50px;
                    margin-top: -25px;
                    text-align: center;
                }
                .equment_error .error_details .item1 li{
                    position: absolute;
            
                    color: white;
                    font-size: 50px;
                    margin-left: 150px;
                    margin-top: -65px;
                }
             .equment_error .error_details .item2
         {
           width: 200px;
            height:150px;
            background: linear-gradient(#FBAE54,#FCA034,#FEC578);
            border-radius: 6px;
            margin-left: 10px;
         }
             .equment_error .error_details .item2:hover{
                background: linear-gradient(#53DEE3, #70EEFA, #75F0FF);
             }
                .equment_error .error_details .item2 h4{
                        margin-left: 5px;
                        margin-top: 5px;
                        color: white;
                        font-size: 25px;
                }
                .equment_error .error_details .item2 p{
                    color: white;
                    font-size: 50px;
                    margin-top: -25px;
                    text-align: center;
                }
                .equment_error .error_details .item2 li{
                    position: absolute;
                    color: white;
                    font-size: 50px;
                    margin-top: -65px;
                    margin-left: 120px;
                }
             .equment_error .error_details .item3
         {
           width: 200px;
             height:150px;
            background: linear-gradient(#AAAAAA,#7F7F7F,#AAAAAA);
            border-radius: 6px;
            margin-left: 10px;
         }
             .equment_error .error_details .item3:hover{
                background: linear-gradient(#53bae3, #70bcfa, #75c8ff);
             }
                .equment_error .error_details .item3 h4{
                        margin-left: 5px;
                        margin-top: 5px;
                        color: white;
                        font-size: 25px;
                }
                .equment_error .error_details .item3 p{
                    color: white;
                    font-size: 50px;
                    margin-top: -25px;
                    text-align: center;
                }
                .equment_error .error_details .item3 li{
                    position: absolute;
                    color: white;
                    font-size: 50px;
                    margin-top: -75px;
                    margin-left: 120px;
                }
        .echarts1{
            width: 95vw;
            height: 50vh;
           
        }
        .bar2{
            height: 50vh;
            margin-left: 30px; background: #0e0525;
        }
</style>