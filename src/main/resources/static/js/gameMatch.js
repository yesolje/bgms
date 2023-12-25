/**-----------------------------------------------------------------------------------------
 * Description : gameMatch.js
 **----------------------------------------------------------------------------------------*/
/********************************************************************************
 * Global Variable : 전역변수 정의
 ********************************************************************************/
var player;
var table;

 /********************************************************************************
  * Document Ready
  ********************************************************************************/

document.addEventListener("DOMContentLoaded", async function(){
    player = await gameMatch.getAllPlayer();
    console.log(player);
    gameMatch.initTabulatorLoad();
});

var gameMatch={
    viewPlayer: async function(){
        player = await gameMatch.getAllPlayer();
        console.log(player);
    },
    getAllPlayer:function(){
        try{
            const response = fetch("/viewPlayer",{
                method:"POST",
                headers:{'Content-Type': 'application/json'},
                });
            return response.then(res=>res.json());
        }catch(error){
            console.log("플레이어 가져오기 중 에러 발생",error);
        }
    },
    initTabulatorLoad:function(){
        table = new Tabulator("#playerTabulator", {
            data:player,
            layout:"fitColumns",
            pagination:false,
            //paginationSize:5,
            columns:[
                {title:"코드", field:"memberCode",width:120},
                {title:"이름",  field:"memberName"},
                {title:"급수",   field:"gradeCode"},
                {title:"성별", field:"sex" },
                {title:"게임수", field:"playCount"},
                {title:"참여", field:"attend",     visible:false},
            ],
        });
    },

}

