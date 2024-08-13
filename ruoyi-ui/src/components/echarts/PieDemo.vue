<!--不同成员数据对比数据-->
<template>
    <div class="bar" id="pie1"></div>
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
    name:"pieDemo",
    props:["PieDate","title"],
    data(){
        return{
            data:this.PieDate
        }
    },
    watch:{
        PieDate(newdata,old){
            this.data=newdata
            this.drawPie()
        }
    },
    mounted(){
        this.drawPie()
    },
    methods:{
        drawPie(){
            var chartDom = document.getElementById('pie1');
            var myChart = echarts.init(chartDom);
            var option;
            var date= this.data
            option = {
                 color:[
                    "#66CCCC",
                    "#CCFF66",
                    "#FF99CC",
                    "#666699",
                    "#FF9999",
                    "#FF9900",
                    "#FFFF66"
                    ],
                title: {
                    text: '设备使用情况',
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
                        name: '本类型花费',
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
<style scoped>
    .bar{
        min-width: 600px;
        height: 500px;
    }
</style>