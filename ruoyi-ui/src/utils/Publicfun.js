export function getCurrentMonthLast(date){
    var endDate = new Date(date); //date 是需要传递的时间如：2018-08
    var month=endDate.getMonth();
    var nextMonth=++month;
    var nextMonthFirstDay=new Date(endDate.getFullYear(),nextMonth,1);
    var oneDay=1000*60*60*24;
    var dateString=new Date(nextMonthFirstDay-oneDay);
    return dateString.toLocaleDateString(); 
}
/**判断某事件端是否已经被预约  当天 */
export function Flagfor(DateAll,start,end){
    for(var i = 0;i < DateAll.length; i++){
          var time1 = String(start + ":00");
          var time2 = String(end + ":00");
          var time3 = String(DateAll.subscribeStartTime);
          var time4 = String(DateAll.subscribeEndTime)
          if((time1 >= time3 && time1 <= time4) || (time2 >= time3 && time2 <= time4) || (time1 <= time3 && time2 >= time4)){
            this.msgError("该时间段已经被预约")
          }
        }
  }