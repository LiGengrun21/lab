<template>
    <div class="fixBar" id="main">
    </div>
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
    props:["date"],
    data(){
        return{
            data:this.date,
            sorce:[['事件', '2012', '2013', '2014', '2015', '2016', '2017',"2018","2019","2020","2021","2022","2023"],
                    ['类型一', 56.5, 82.1, 88.7, 70.1, 53.4, 85.1,56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
                    ['类型二', 51.1, 51.4, 55.1, 53.3, 73.8, 68.7,51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
                    ['类型三', 40.1, 62.2, 69.5, 36.4, 45.2, 32.5,40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
                    ['类型四', 25.2, 37.1, 41.2, 18, 33.9, 49.1,25.2, 37.1, 41.2, 18, 33.9, 49.1]]
        }
    },
    mounted(){
            this.drawfix()
    },
    methods:{
        drawfix(){
      var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;
        setTimeout(function () {
            option = {
                 title: {
                    text: '（成员——PI）不同类型的设备花费',
                    subtext: '具体数据',
                    left: 'left'
                },
                 color:[
                  "#5B8FF9",
                    "#61DDAA",
                    "#65789B",
                   "#F6903D",
                    "#7262fd",
                    "#78D3F8",
                    ],
                legend: {},
                tooltip: {
                    trigger: 'axis',
                    showContent: false
                },
                dataset: {
                    source:this.sorce
                },
                xAxis: {type: 'category'},
                yAxis: {gridIndex: 0},
                grid: {top: '55%'},
                series: [
                    {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: {focus: 'series'}},
                    {
                        type: 'pie',
                        id: 'pie',
                        radius: '30%',
                        center: ['50%', '25%'],
                        emphasis: {focus: 'data'},
                        label: {
                            formatter: '{b}: {@2012} ({d}%)'
                        },
                        encode: {
                            itemName: 'product',
                            value: '2012',
                            tooltip: '2012'
                        }
                    }
                ]
            };
            myChart.on('updateAxisPointer', function (event) {
                var xAxisInfo = event.axesInfo[0];
                if (xAxisInfo) {
                    var dimension = xAxisInfo.value + 1;
                    myChart.setOption({
                        series: {
                            id: 'pie',
                            label: {
                                formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                            },
                            encode: {
                                value: dimension,
                                tooltip: dimension
                            }
                        }
                    });
                }
            });
            myChart.setOption(option);
        });
        option && myChart.setOption(option);
    },
    }
}
</script>
<style scoped>

</style>