<!--不同设备数据对比数据-->
<template>
    <div class="bar" id="pie2"></div>
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
    props:["PieDate"],
    data(){
        return{
            data:this.PieDate
        }
    },
    mounted(){
        this. drawPie2()
    },
    methods:{
         drawPie2(id){
            var chartDom = document.getElementById(id);
            console.log( document.getElementById('pie2'))
            var myChart = echarts.init(chartDom);
            var option;
            var date= this.piedate2
            option = {
                 color:[
                    "#B8E1FF",
                    "#9AC5FF","#3D76DD",
                    "#0047A5",
                    "#5B8FF9",
                    "#3D76DD",

                    ],
                title: {
                    text: '不同成员的设备花费占比',
                    subtext: '不同成员的设备花费',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                },
                series: [
                    {
                        name: '成员花费',
                        type: 'pie',
                        radius: '50%',
                        data: date,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
     if (option && typeof option === "object") {
          myChart.setOption(option);
      }


    },
    }
}
</script>