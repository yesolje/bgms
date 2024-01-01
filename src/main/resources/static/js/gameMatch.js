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
                {title:"코드", field:"memberCode",width:170},
                {title:"이름",  field:"memberName"},
                {title:"급수",   field:"gradeCode"},
                {title:"성별", field:"sex" },
                {title:"게임수", field:"playCount"},
                {title:"참여", field:"attend",     visible:false},
            ],
        });
    },
    addCourt:function(){
       //코트추가 버튼을 눌러 코트를 추가한다.
       //코트는 iframe 으로 이루어지며 id 값으로 고유 코트값을 갖는다.
        var courtId = gameMatch.generateRandomCourtId();
        console.log(courtId);

        var iframe = document.createElement('iframe');
        iframe.src = '/iframe/court.html'; // 로드할 외부 HTML 파일의 경로로 수정
        //iframe.frameBorder = '0'; // 외곽선 제거
        // iframe을 추가할 영역 가져오기
        var container = document.querySelector('.container_gameMatch');

        // iframe을 영역에 추가
        container.appendChild(iframe);


    },
    deleteCourt:function(){

    },
    generateRandomCourtId: function(){
      var characters ='1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ';
      var result = '';
      var charactersLength = characters.length;
      for (let i = 0; i < 20; i++) {
          result += characters.charAt(Math.floor(Math.random() * charactersLength));
      }

      return result;
    },

}

