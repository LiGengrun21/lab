<template>
    <div>
        <el-calendar>
            <template slot="dateCell" slot-scope="{date, data}" class="calItem" >
                <div :class="data.isSelected ? 'is-selected' : ''" @click="calClick(data)">
                    <p class="dayItem"  v-if="data.day.substr(-2) < 10">{{ data.day.substr(-1)}}</p>
                    <p class="dayItem"  v-else>{{ data.day.substr(-2)}}</p>
                    <div v-for="(item,index) in calendarData" :key="index">
                        <div v-if="(item.years).indexOf(data.day.split('-').slice(0)[0])!=-1 && (item.months).indexOf(data.day.split('-').slice(1)[0])!=-1 && (item.days).indexOf(data.day.split('-').slice(2).join('-'))!=-1">
                            <el-tooltip :content="item.things" placement="right">
                                <div class="mark">{{item.things}}</div>
                            </el-tooltip>
                        </div>
                        <div v-else></div>
                    </div>
                    <p class="addBtn" v-show="data.isSelected == true" @click="dialogVisible = true">添加日程</p>
                </div>
            </template>
        </el-calendar>
        <el-dialog
            :title="formData.data"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
            <el-form @submit.native.prevent>
                <el-form-item label="日程">
                    <el-input v-model="formData.content"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false;add()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "calendar",
    data(){
        return {
            formData:{
                data:'',
                content: ''
            },
            dialogVisible: false,
            calendarData: [
                { years: ['2021'], months: ['09', '11'],days: ['14'],things: '杂志' },
                { years: ['2019'], months: ['10', '11'], days: ['02'],things: '演唱会' },
                { years: ['2020'], months: ['11'], days: ['02'],things: '晚会' },
                { years: ['2019'], months: ['11'], days: ['02'],things: '杂志预售' },
                { years: ['2020'], months: ['07'], days: ['15'],things: '重启开播' }
            ],
            value: new Date()
        }
    },
    methods: {
        calClick(item){
            console.log(item)
            this.formData.data = item.day
        },
        handleClose(done){
            done()
        },
        add(){
            var date = this.formData.data.split('-')
            var a =  { 
                years: [date[0]],
                months: [date[1]],
                days: [date[2]],
                things: this.formData.content }
            this.calendarData.push(a)
        }
    }
}
</script>
<style >
.calendar-day{
    text-align: center;
    color: #202535;
    line-height: 30px;
    font-size: 12px;
}
.is-selected{
    color: #F8A535;
}
.mark{
    padding: 8px 8px 0 8px;
    color: #F8A535;
    z-index: -1;

}
#calendar .el-button-group>.el-button:not(:first-child):not(:last-child):after{
    content: '当月';
}
.el-backtop, .el-calendar-table td.is-today{
    color: #F8A535!important;
}
.calItem{
    /* font-size: 20px */
    overflow: hidden;
}
.dayItem{}
/* .el-calendar-table .el-calendar-day{
    position: relative!important;
} */
.addBtn{
    position: absolute;
    z-index: 99;
    display: block;
    width: 65px;
    height: 20px;
    padding: 9px;
    background: rgba(0, 200, 156, .6);
    color: #fff;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    border-radius: 5px
}
.addBtn:hover{
    background: rgba(0, 200, 156, 1);
}
</style>
<style>
.dayItem{
    font-size: 35px;
    position: absolute;
    width: 100%;
    height: 85px;
    text-align: center;
    line-height: 85px;
    margin: 0;
    z-index: 1;
}
.current .dayItem{
    color: #3A7;
    opacity: 0.5;
}
.el-calendar-table:not(.is-range) td.next, .el-calendar-table:not(.is-range) td.prev {
    color: #C0C4CC;
    background: #fafafa;
}

</style>
