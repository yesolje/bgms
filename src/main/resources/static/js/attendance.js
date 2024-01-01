/**-----------------------------------------------------------------------------------------
 * Description : attendance.js
 **----------------------------------------------------------------------------------------*/
/********************************************************************************
 * Global Variable : 전역변수 정의
 ********************************************************************************/
var member;
var table;
var nowTablePage = 1;
 /********************************************************************************
  * Document Ready
  ********************************************************************************/

document.addEventListener("DOMContentLoaded", async function(){
    member = await attendance.getAllMember();
    console.log(member);
    attendance.initTabulatorLoad();

    document.querySelector("#searchArea").addEventListener("keyup", attendance.updateFilter);
});



var attendance={

    getAllMember:function(){
        try{
            const response = fetch("/viewMember",{
                method:"POST",
                headers:{'Content-Type': 'application/json'},
                });
            return response.then(res=>res.json());
        }catch(error){
            console.log("멤버 가져오기 중 에러 발생",error);
        }
    },
    initTabulatorLoad:function(){
        table = new Tabulator("#memberTabulator", {
            data:member,
            layout:"fitColumns",
            pagination:true,
            paginationSize:5,
            columns:[
                { title: "#",   field: "index",      formatter: "rownum", width:70  },
                {title:"코드",   field:"memberCode",  width:170 },
                {title:"이름",   field:"memberName" },
                {title:"급수",   field:"gradeCode"},
                {title:"성별",   field:"sex"  },
                {title:"게임수", field:"playCount" ,   visible:false},
                {title:"출석체크" , width:130,     formatter:attendance.buttonFormatter },
            ],
        });
    },

    //list tabulator row 관리 버튼 함수
    buttonFormatter:function(cell, formatterParams, onRendered){
        var playYn = cell.getData().playCount;
        if(playYn != null){
            return '<button class="btn btn-secondary" onclick="attendance.handleButtonClick(\'' + cell.getData().memberCode+'\',\''+cell.getData().playCount+ '\')">참석중</button>';
        }else{
            return '<button class="btn btn-primary" onclick="attendance.handleButtonClick(\'' + cell.getData().memberCode+'\',\''+cell.getData().playCount+ '\')">미참석</button>';
        }

    },

    handleButtonClick:async function(memberCode, playCount){
        nowTablePage = table.getPage();
        if( playCount != 'null'){ //참석자를 불참으로 변경
            var resultCount = await attendance.changeAttendStatus(memberCode,"Cancel");
            member = await attendance.getAllMember();
            table.setData(member);
            table.setPage(nowTablePage);
        }else{ //불참자를 참석으로 변경
            var resultCount = await attendance.changeAttendStatus(memberCode,"Attend");
            member = await attendance.getAllMember();
            table.setData(member);
            table.setPage(nowTablePage);
        }
    },

    changeAttendStatus:function(memberCode, action){
        let data = {
            member_code:memberCode,
            act:action
        }
        try{
            const response = fetch("/changeAttendStatus",{
                method:"POST",
                body:JSON.stringify(data),
                headers:{'Content-Type': 'application/json'},
                });

            return response.then(res=>res.json());
        }catch(error){
            console.log("back 단 수행 중 에러 발생",error);
        }
    },

    updateFilter : function () {
        var filterValue = document.querySelector('#searchArea').value;
        table.setFilter("memberName","like", filterValue);

    },
}

