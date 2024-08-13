<!--成员数据对比数据-->
<template>
    <div class="bar" id="barline"></div>
</template>
<script>
let echarts = require('echarts/lib/echarts');
// 引入柱状图
require('echarts/lib/chart/bar');
// 引入饼图
require('echarts/lib/chart/pie');
require("echarts/lib/chart/line")
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');

export default {
    name:"lines",
    props:["barlabel","barValue"],
    data(){
        return{
            bardatalabel:this.barlabel,
            bardataValue:this.barValue
        }
    },
    watch:{
        barlabel(newData,olddata){
            this.bardatalabel=newData
        },
        barValue(newData,olddata){
            this.bardataValue=newData
        }
    },
    mounted(){
        this.drawdweek()
    },
    methods:{
        //横行柱形图
    drawdweek(){
      var dom = document.getElementById("barline");
      var myChart1 = echarts.init(dom);
      var app = {};
        var category = this.bardatalabel
        var barData = this.bardataValue
        var option1 = {
            title: {
                text: '不同成员的设备费用对比',
                    textStyle: {
                        color: '#03a9f4'
                    }
            },
        legend: {
            data:['用户数']
        },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '16%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: "rgb(54, 54, 54)"
                    }
                },
                axisLabel: {
                    rotate:45,      //倾斜度 -90 至 90 默认为0
                    show: false
                },
                axisTick: {
                    show: false
                },
                splitLine: {           // 分隔线
                    show: false,        // 默认显示，属性show控制显示与否
                },
            },


            yAxis: {
                type: 'category',
                data: category,

                splitLine: {           // 分隔线
                    show: false,        // 默认显示，属性show控制显示与否
                },
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: "rgb(54, 54, 54)"
                    }
                },
                axisTick: {
                    show: false
                },
                offset: 10,
            },
            series: [
                {
                    name: '数量',
                    type: 'bar',
                    data: barData,
                    barWidth: 14,
                    // barGap: 10,
                    // smooth: true,
                    label: {
                        normal: {
                            show: true,
                            position: 'right',
                            offset: [5, -2],
                            textStyle: {
                                color: '#F68300',
                                fontSize: 13
                            }
                        }
                    },
                    itemStyle: {
                        emphasis: {
                            barBorderRadius: 7
                        },
                        normal: {
                            barBorderRadius: 7,
                            color: function(params){
                                var colorList = [
                                    ['#98F898','#66CDAA'],
                                    ['#66CDAA','#2E8B57'],
                                   
                                ]
                                for(let i=0;i<barData.length;i++){
                                    if(barData[params.dataIndex] <= 3000){
                                        return new echarts.graphic.LinearGradient(
                                            0, 0, 1, 0,
                                            [
                                                {offset: 0, color: '#98F898'},
                                                {offset: 1, color: '#66CDAA'}
                                            ])
                                    }else{
                                        return new echarts.graphic.LinearGradient(
                                            0, 0, 1, 0,
                                            [
                                                {offset: 0, color: '#98F898'},
                                                {offset: 0.5, color: '#66CDAA'},
                                                {offset: 1, color: '#2E8B57'}

                                            ])
                                    }
                                }
                            }
                        },
                    },
                },

            ]
        };
      if (option1 && typeof option1 === "object") {
          myChart1.setOption(option1, true);
      }
    },
    }
}
</script>
<style>
.bar{
    width: 500px;
    height: 500px;
}
</style>


