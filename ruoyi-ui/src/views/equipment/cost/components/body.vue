<template>
    <div class="equmentBody">
        <el-form :data="search_from" label-position="left" label-width="70px" class="search" :inline="true">
            <el-form-item label="成员类型" width="220" >
                <el-select v-model="search_from.numtype">
                    <el-option v-for="(item,index) in NumType" :key="index" :label="item.label" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="PI组搜索"  width="220">
               <el-select v-model="search_from.pi"
                    :fetch-suggestions="querySearch"
                    placeholder="请输入内容"
                    @select="handleSelect"
                    width="120">
                    <el-option v-for="(item,index) in PIs" :key="index" :label="item.value" :value="item.label"></el-option></el-select>
            </el-form-item>
            <el-row>
            <el-form-item label="日期类型">
                <el-select v-model="search_from.type">
                    <el-option v-for="(item,index) in MonthType"
                    :key="index"
                    :value="item.value"
                    :label="item.label">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item  label="日期选择" >
                <el-date-picker
                    :type="search_from.type"
                    v-model="search_from.day"  width="200"
                    :value-format="search_from.type==='dates'?'yyyy-MM-dd':'yyyy-MM'"
                    placeholder="时间选择">
                    </el-date-picker>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm" >检索</el-button>
            </el-form-item>
            </el-row>
        </el-form>

        <div class="button">
             <el-button type="warning" class="el-icon-set-up" plain >筛选</el-button>
            <el-button type="danger"  class="el-icon-search" plain>费用总览</el-button>
            <el-button type="success" plain  class="el-icon-download"  >导出表格</el-button>
        </div>

        <div class="line"></div>
        <div class="main">
            <el-table
                :data="costTable"
                :header-cell-style="{color:'#000000','text-align': 'center'}"
                style="width: 100%">
                <el-table-column
                    type="selection"
                    width="55">
                    </el-table-column>
                <el-table-column
                label="成员名字"
                align="center"
                width="100">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.name }}</span>
                </template>
                </el-table-column >
                <el-table-column align="center"
                label="PI组"
                width="100" prop="name">
                </el-table-column>
                 <el-table-column
                label="预约记录"
                width="100"
                align="center"
                :filters="filter"
                :filter-method="filterHandler"
                prop="count"
                >
                </el-table-column>
                 <el-table-column
                label="设备使用费用"
                width="130"
                sortable
                align="center"
                prop="equipmentMoney">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                    <div slot="reference" class="name-wrapper" style="disply:flex">
                        <el-tag style="height:30px;width:50px;">{{scope.row.equipmentMoney}}</el-tag>

                    </div>
                    </el-popover>
                </template>
                </el-table-column>
                  <el-table-column
                label="耗材费用"
                width="120"
                sortable
                align="center"
                prop="materialMoney">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                    <div slot="reference" class="name-wrapper" style="disply:flex">
                        <el-tag style="height:30px;width:50px;">{{scope.row.materialMoney}}</el-tag>
                    </div>
                    </el-popover>
                </template>
                </el-table-column>
                  <el-table-column
                label="人工服务费用"
                width="140"
                sortable
                align="center"
                prop="artificialMoney">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                    <div slot="reference" class="name-wrapper" style="disply:flex">
                        <el-tag style="height:30px;width:50px;">{{scope.row.artificialMoney}}</el-tag>

                    </div>
                    </el-popover>
                </template>
                </el-table-column>
                <el-table-column
                label="费用总计"
                width="120"
                sortable
                align="center"
                prop="totalMoney">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                    <div slot="reference" class="name-wrapper" style="disply:flex">
                        <el-tag style="height:30px;width:50px;">{{scope.row.totalMoney}}</el-tag>
                    </div>
                    </el-popover>
                </template>
                </el-table-column>
              <el-table-column
                label="实际费用"
                width="120"
                sortable
                align="center"
                prop="totalMoney">
                <template slot-scope="scope">
                  <el-popover trigger="hover" placement="top">
                    <div slot="reference" class="name-wrapper" style="disply:flex">
                      <el-tag style="height:30px;width:50px;">{{scope.row.actualTotalMoney}}</el-tag>
                    </div>
                  </el-popover>
                </template>
              </el-table-column>
                <el-table-column
                label="操作"
                align="center"
                >
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    type="text"
                    class="el-icon-view"
                    @click="viewDetail(scope.row)"
                    >权限查看</el-button>
                </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
import {store}  from "@/store/index"
import {listCost,getSubscribesByPiUni, getPiMembers,getUseCost} from "@/api/equipment/cost"
import {GetAAllPis2,GetPiuser,listUser,} from "@/api/system/user"
export default {
    data(){
        return{
            NumType:[{label:"成员",value:"user"},{label:"Pi组",value:"Pi"}],
            //所有的PI
            PIs:[],
            search_from:{
                userId:0,
                pi:1
            },
            //数据选择
            queryParams: {
                pageNum: 1,
                pageSize: 10,
            },
            //时间阶段
            daterange:["7:00--10:00","10-8:00"],
             //对话狂是否打开
            open:false,
            //对话框表单
            form:{},
            //规则校验
            rules:{},
            //table内容
            equmentList:[],
            //身份筛选
            filter:[{text:"PI负责人",value:"PI负责人"},{text:"PI助理",value:"PI助理"},{text:"成员",value:"成员"}],
            //我的id
            myid:1,
            //搜索的成员名单
            userList:[],
            userForm:{piId:1},
            costTable:[],
            MonthType:[{label:"多日",value:"dates",valuetype:"yyyy-MM-dd"},{label:"月份",value:"month",valuetype:"yyyy-MM"},]
        }
    },
    mounted(){
        this.GetCostPi()
        this.GetCost()
        this.GetCostuser()
    },
    methods:{
        async submitForm(){
            //时间的选择 一位时间段
            if(this.search_from.type=="dates")
            {
                const beginDate=this.search_from.day[0]
                const endDate=this.search_from.day[1]
                this.GetDetailsByid({piId:this.search_from.pi,beginDate:beginDate,endDate:endDate})
            }
            else{
                const beginDate=this.search_from.day+"-01"
                const endDate=this.getCurrentMonthLast(beginDate)
                this.GetDetailsByid({piId:this.search_from.pi,beginDate:beginDate,endDate:endDate})
            }
           },
    getCurrentMonthLast(date){
                var endDate = new Date(date); //date 是需要传递的时间如：2018-08
                var month=endDate.getMonth();
                var nextMonth=++month;
                var nextMonthFirstDay=new Date(endDate.getFullYear(),nextMonth,1);
                var oneDay=1000*60*60*24;
                var dateString=new Date(nextMonthFirstDay-oneDay);
                return dateString.toLocaleDateString();

            },
    //根据piid  获取下属用户消费信息
    async  GetDetailsByid(form){
        try{
        const res=await  getPiMembers(form)
        console.log(res.data)
        this.costTable=res.data
        }catch(err){
            console.log(err)
        }
    },
    /**获取月份数据
     *
     */
    //获取所有皮的消费信息
    async GetCost(){
      const res=await getSubscribesByPiUni()
      console.log(res.data)
      this.costTable=res.data
    },
       //获取所有pi
       async GetCostPi(){
           const res=await GetAAllPis2()
           this.PIs=res.data.map(function(item){
               return {value:item.nickName,label:item.userId}
           })
       },
       //获取所有的下属
       async GetCostuser(index){
            GetPiuser({piId:this.search_from.pi}).then(res=>{
                console.log("下属")
                console.log(res)
            })
       },
        /**获取设备列表 */
        GetEquments(){
            console.log(store.state.userinfor)
        },
        /**切换对话框是否展现 */
        changeStatus(){
            this.open=true
        },
        cancel(){
            this.open=false
        },
        filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      handleSelect(item) {
        console.log(item);
        this.search_from.userId=item.label
      },
      querySearch(queryString, cb) {
        var PIs = this.PIs;
        var results = queryString ? PIs.filter(this.createFilter(queryString)) : PIs;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (PIs) => {
          return (PIs.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
    }
}
</script>
<style scoped>
    .equmentBody{
        padding: 50px;

    }
    .search{
        padding: 10px;
        padding-top: 10px;
        margin-top: -30px;
        border-radius: 10px;
       box-shadow: 0 0 8px 5px rgba(228, 228, 228, 0.596);
    }
    .el-form-item{
        margin-left: 30px;
    }
    .el-form-item .el-input{
        width: 205px;
    }
    .button{

        padding-top: 10px;
        padding-left: 30px;
        margin-top: 10px;

        font-size: 20px;
    }
    .el-table{
        padding-left: 30px;

        text-align: center !important;
    }
    .el-table-column{
        text-align: center !important;
    }
    .el-table th{
         text-align: center !important;
    }
     .el-table td{
         text-align: center !important;
    }
    .el-date-picker{
        width: 250px;
    }
    .line{
        width: 90%;
        height: 4px;
        background: rgba(218, 217, 217, 0.164);
    }
</style>
