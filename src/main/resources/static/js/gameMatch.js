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
    //플레이어 리스트 가져오기
    viewPlayer: async function(){
        player = await gameMatch.getAllPlayer();
        console.log(player);
    },

    //플레이어 리스트 가져오기 - 서버통신(비동기)
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
    //table 최초생성
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

    //신규 코트 추가
    addCourt:function(){
        var courtId = gameMatch.generateRandomCourtId();
        var crt = gameMatch.COURT_FRAME_NEW(courtId);
        var container = document.querySelector('.container_gameMatch');
        container.innerHTML += crt;
    },

    //특정 코트 삭제
    deleteCourt:function(courtId){
         var element = document.getElementById(courtId);
         element.parentNode.removeChild(element);
    },

    //코트 아이디 생성(난수)
    generateRandomCourtId: function(){
      var characters ='1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ';
      var result = '';
      var charactersLength = characters.length;
      for (let i = 0; i < 20; i++) {
          result += characters.charAt(Math.floor(Math.random() * charactersLength));
      }
      return result;
    },

    //게임 짜는 버튼 눌렀을 때 코트 내부를 다시 그려줌
    makeGameAuto:function(type,courtId){
        var matchCourt = gameMatch.COURT_FRAME_MATCH(courtId);
        var unMatchCourt = document.getElementById(courtId);
        unMatchCourt.outerHTML = matchCourt;

    },

    //코트 아이디로 코트를 찾아서 버튼영역과 코트 영역을 초기화 시켜줌
    refreshCourt:function(courtId){
        newCourtId = gameMatch.generateRandomCourtId();
        var newCourt = gameMatch.COURT_FRAME_NEW(newCourtId);
        var oldCourt = document.getElementById(courtId);
        oldCourt.outerHTML = newCourt;
    },

    //게임시작 버튼을 누르면 플레이어 4명과 코트 번호를 서버로 전송, 버튼은 게임완료 버튼으로 바뀐다.
    startGame:function(courtId){

    },



///////코트 프레임 모음 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////



    //코트 프레임(초기화상태)
    COURT_FRAME_NEW :function (courtId){
        var crt = `
                  <div class="container_court" id=${courtId}>
                      <div class="container_court_button">
                          <button class="btn btn-secondary" onclick="gameMatch.deleteCourt('${courtId}')"><img src="/images/trash.png" class="trash_image" alt="코트삭제"></button>
                          <button class="btn btn-primary" onclick="gameMatch.makeGameAuto('male','${courtId}')">남복</button>
                          <button class="btn btn-primary" onclick="gameMatch.makeGameAuto('female','${courtId}')">여복</button>
                          <button class="btn btn-primary" onclick="gameMatch.makeGameAuto('mixed','${courtId}')">혼복</button>
                          <button class="btn btn-primary" onclick="">수동</button>
                      </div>
                      <div class="container_court_playground">
                          <img src="/images/court_6.png" class="game_court_image" alt="Badminton court image">
                      </div>
                  </div>
                   `;
        return crt;
    },
    //코트 프레임(진행중상태)
    COURT_FRAME_MATCH :function (courtId){
        var crt = `
                <div class="container_court" id=${courtId}>
                    <div class="container_court_button">
                        <button class="btn btn-secondary" onclick="gameMatch.refreshCourt('${courtId}')">다시매칭</button>
                        <button class="btn btn-primary" onclick="gameMatch.startGame('${courtId}')">게임시작</button>
                    </div>
                    <div class="container_court_playground">
                        <table class="container_court_playGround_player">
                          <tr>
                              <td class="container_court_playGround_player_detail">
                                  <div class="player">
                                      <a>홍길동E1</a>
                                  </div>
                              </td>
                              <td class="container_court_playGround_player_detail">
                                  <div class="player">
                                      <a>홍길동A</a>
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td class="container_court_playGround_player_detail">
                                  <div class="player">
                                      <a>홍길동B</a>
                                  </div>
                              </td>
                              <td class="container_court_playGround_player_detail">
                                  <div class="player">
                                      <a>홍길동E2</a>
                                  </div>
                              </td>
                          </tr>
                        </table>
                        <img src="/images/court_6.png" class="game_court_image" alt="Badminton court image">
                    </div>
                </div>
                `;
        return crt;
    },

}

