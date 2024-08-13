<!--用户设备界面-->
<template>
  <div class="equment">
  <!-- 预约流程查看 -->
  
  <div class="body">
    <Tablebody ></Tablebody>
  </div>
  </div>
</template>

<script>
import tablebody from "./components/body.vue"
export default {
  components:{
       "Tablebody": tablebody
  },
  data(){
    return{
      //流程图是否展示
      flow_open:true,
      //规则校验
       rules:{},


    }
  },methods: {
           
           cancel(){
              this.flow_open=false
           },
           changeopen(i){
             this.open=i
           }
          


    },
}
</script>
<style  scoped>
.flow{
  width: 90%;
  margin-left: 50px;
  margin-top: 50px;
}
</style>
