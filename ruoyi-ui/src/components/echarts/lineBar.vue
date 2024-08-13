<!--
    直方图
-->
<template>
    <div class="linebar" id="lineb"></div>
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
    name:"Linebar",
    props:["label","value","title"],
    data(){
        return{
            datalabel: this.label,
            dataValue:this.value,
            datatitle:this.title,
        }
    },
    watch:{
        label(newvalue){
            this.datalabel=newvalue
        },
        value(newdata){
            this.dataValue=newdata
            this.drawLine()
        },
        title(newdata){
            this.datatitle=newdata
        }

    },
    mounted(){
        this.drawLine()
    },
    methods:{
         drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('lineb'))
      myChart.setOption({
        title: { text: this.datatitle },
        tooltip: {},
        color: ['#00c3e5'],
        xAxis: {
          data: this.datalabel,
        },
        yAxis: {
          minInterval: 1
        },
        series: [{
          name: '次',
          type: 'bar',
          data: this. dataValue,
          itemStyle: {
            emphasis: {
                barBorderRadius: 7
            },
            normal: {
                barBorderRadius: 10,
                color: function(params){
                    
                            return new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#FF7F50'},
                                    {offset: 1, color: '#FFDEAD'}
                                ])
                        
                        
                    }
                }
            },
                    
        }]
      });
    },
    }
}
</script>
<style>
.linebar{
    width: 500px;
    height: 500px;
}
</style>