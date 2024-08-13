<template>
        <div id="container"></div>
</template>
<script>
let echarts = require('echarts');

export default {
    name:"acrossBar",
    props:["date"],
    data(){
        return{
            data:this.date,
            
        }
    },
    watch:{
        date( newdata){
          this.data=newdata
        }
        },
    mounted(){
        this. drawBar()
    },
   methods:{
        drawBar(){
        // 基于dom，初始化echarts实例

        let data=this.data;
        console.log(this.data)
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
                       data : data.days,
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
                        data:data.value
                   },

               ]
           };
           let myChart = echarts.init(document.getElementById("container"));
           myChart.setOption(option);
      },
    
	
				//box3画图
   }
}
</script>
