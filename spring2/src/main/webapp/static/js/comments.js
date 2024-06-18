/**
 * /post/details.jsp에 포함.
 */

 document.addEventListener('DOMContentLoaded', () => {
    // btnToggleComment 버튼 요소를 찾음.
    const btnToggleComment = document.querySelector('button#btnToggleComment');
    
    // collapseComments div 요소를 부트스트랩의 Collapse 객체로 생성.
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', {toggle: false}); // false는 접혀 있는 상태. {toggle: false}는 객체
    
    // 댓글 토글 버튼에 클릭 이벤트 리스너를 등록.
    btnToggleComment.addEventListener('click', () => {
        bsCollapse.toggle();
        
        if (btnToggleComment.innerHTML === '댓글 보기') {
            btnToggleComment.innerHTML = '댓글 감추기';
            // 포스트에 달려있는 모든 댓글 목록 보여줌.
            getAllComments();
        } else {
            btnToggleComment.innerHTML = '댓글 보기';
        }
    });
    
    // 버튼 btnRegisterComment 요소를 찾음.
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    
    // 부트스트랩 모달(다이얼로그) 객체 생성.
    const commentModal = new bootstrap.Modal('div#commentModal', {backdrop: true});
    
    // 모달의 저장 버튼을 찾고, 클릭 이벤트 리스너를 설정.
    const btnUpdateComment = document.querySelector('button#btnUpdateComment');
    btnUpdateComment.addEventListener('click', updateComment);
    
    /*----------------------------------------------------------------------------------*/
    
    // 버튼에 클릭 이벤트 리스너를 등록.
    btnRegisterComment.addEventListener('click', registerComment); // 클릭 이벤트 처리를 하는 registerComment 함수를 만듦.
    
    // 댓글 등록 이벤트 리스너 콜백(함수):
    function registerComment() {
        // 댓글이 달릴 포스트 번호를 찾음.
        const postId = document.querySelector('input#id').value;
        
        // 댓글 내용을 찾음.
        const ctext = document.querySelector('textarea#ctext').value;
        
        // 댓글 작성자 아이디를 찾음.
        const username = document.querySelector('input#username').value;
        
        // 댓글 내용, 댓글 작성자가 비어있는지 체크
        if (ctext === '' || username === '') {
            alert('댓글 내용과 작성자는 반드시 입력하세요.');
            return; // 이벤트 리스너를 종료
        }
        
        // Ajax 요청에서 보낼 데이터 객체를 생성.
        /* const data = {
            postId : postId,
            ctext : ctext,
            username : username       
        }; */
        const data = {postId, ctext, username}; // 위에꺼랑 같은 의미. CommentCreateDto의 필드 이름과 동일하게 만듦.
        console.log(data); // {postId: '46', ctext: 'ajax 요청 테스트', username: 'test'}
        
        // POST 방식의 Ajax 요청을 보냄. 응답 성공/실패 콜백을 등록.
        axios
        .post('../api/comment', data)
        .then((response) => { // 성공했을 때의 함수
            // console.log(response); // {data: 1, status: 200, statusText: '', headers: r, config: {…}, …} // 서버에서 보낸 데이터는 data
            console.log(response.data); // RestController에서 보낸 응답 데이터(int result)
            if (response.data === 1) { // result가 int 타입이라서
                alert('댓글 1개 등록 성공');
                document.querySelector('textarea#ctext').value = '';
                document.querySelector('input#username').value = '';
                // 댓글 목록 갱신
                getAllComments();
            }
        }) // 컨트롤러에서 응답받은 내용을 브라우저가 콘솔창에 띄움. (컨트롤러의 리턴값을 받은 이후에 실행됨.)
        .catch((error) => { // 실패 콜백
            console.log(error);
        });
    }
    
    // 포스트에 달려있는 모든 댓글 목록 가져오기
    function getAllComments() {
        // 댓글 목록을 요청하기 위한 포스트 번호
        const postId = document.querySelector('input#id').value;
        
        // 댓글 목록을 요청하기 위한 REST API URI
        const uri = `../api/comment/all/${postId}`;
        
        // Ajax 요청을 보냄.
        axios
        .get(uri)
        .then((response) => {
            console.log(response.data);
            // 댓글 목록을 HTML로 작성 -> div#comments 영역에 출력.
            makeCommentElements(response.data);
        })
        .catch((error) => {
            console.log(error);
        });
    }
    
    // 댓글 목록(댓글 객체들의 배열)을 아규먼트로 전달받아서 HTML을 작성.
    function makeCommentElements(data) {
        // 댓글 목록 HTML이 삽입될 div
        const divComments = document.querySelector('div#comments');
        
        // 댓글 목록 HTML 코드
        let htmlStr = '';
        for (let comment of data) {
            // 댓글 최종 수정 시간
            const modifiedTime = new Date(comment.modifiedTime).toLocaleString();
            
            htmlStr += `
            <div class="card card-body my-1">
                <div>
                    <span>${comment.id}</span>
                    <span class="fw-bold">${comment.username}</span>
                    <span class="text-secondary">${modifiedTime}</span>
                </div>
                <div>${comment.ctext}</div>
                <div>
                    <button class="btnDeleteComment btn btn-outline-danger btn-sm"
                        data-id="${comment.id}">삭제</button>
                    <button class="btnModifyComment btn btn-outline-primary btn-sm"
                        data-id="${comment.id}">수정</button>
                </div>
            </div>
            `;
        }
        // 모든 html 코드에는 정의된 태그 속성(img src, a href)이 있고,
        // 개발자가 필요로 하는 값들을 사용하기 위한 정의되지 않은 태그 속성(button data-id)이 있음.
        
        // 작성된 HTML 코드를 div 영역에 삽입.
        divComments.innerHTML = htmlStr;
        
        // 모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 설정. (html 코드가 div 영역에 삽입된 이후에 이벤트 발생)
        const btnDeletes = document.querySelectorAll('button.btnDeleteComment');
        for (let btn of btnDeletes){
            btn.addEventListener('click', deleteComment);
            
        }
        
        // 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
        const btnModies = document.querySelectorAll('button.btnModifyComment');
        for (let btn of btnModies) {
            btn.addEventListener('click', showCommentModal);
        }
    }
    
    function deleteComment(event) {
        // 이벤트 리스너 콜백의 아규먼트 event 객체는 target 속성을 가지고 있음.
        console.log(event.target); // 이벤트가 발생한 요소(타겟)
        const id = event.target.getAttribute('data-id'); // HTML 요소의 속성 값 찾기
        
        // 삭제 여부 확인
        const result = confirm('댓글을 정말 삭제할까요?');
        if (!result) { // 사용자가 [취소]를 선택했을 때
            return; // 함수 종료
        }
        
        // Ajax로 삭제 요청을 보낼 REST API URI
        const uri = `../api/comment/${id}`;
        
        // Ajax 요청을 보냄.
        axios
        .delete(uri)
        .then((response) => {
          console.log(response.data);
          if(response.data === 1) {
            alert(`댓글(${id}) 삭제 성공`);
            getAllComments(); // 댓글 목록 갱신
          }
        })
        .catch((error) => {
            console.log(error);
        });
    }
    
    function showCommentModal(event) {
        // 이벤트 타겟(수정 버튼)의 data-id 속성 값을 읽음.
        const id = event.target.getAttribute('data-id');
        
        // Ajax 요청을 보내서 댓글 아이디로 검색.
        const uri = `../api/comment/${id}`;
        axios
        .get(uri)
        .then((response) => {
            console.log(response.data);
            
            // 모달의 input(댓글 번호), textarea(댓글 내용)의 value를 채움.        
            document.querySelector('input#modalCommentId').value = id;
            document.querySelector('textarea#modalCommentText').value = response.data.ctext;
            
            // 모달을 보여줌.
            commentModal.show();
 
        })
        .catch((error) => console.log(error)); // 실행문이 하나일 때 중괄호 생략 가능함.
    }
    
    // 댓글 업데이트 모달의 [저장] 버튼의 클릭 이벤트 리스너
    function updateComment() {
        // 업데이트할 댓글 번호
        const id = document.querySelector('input#modalCommentId').value;
        
        // 업데이트할 댓글 내용
        const ctext = document.querySelector('textarea#modalCommentText').value;
        
        if (ctext === '') {
            alert('업데이트할 댓글 내용을 입력하세요.');
            return; // 이벤트 리스너를 종료
        }
        
        // 댓글 업데이트 요청 REST API URI
        const uri = `../api/comment/${id}`;
        
        // Ajax 요청
        axios
        .put(uri, { ctext }) // { ctext } = {ctext: ctext} id는 CommentRestController에서 dto.setId(id);가 있어서 안 적음.
        .then((response) => {
            console.log(response);
            if (response === 1) {
                alert(`댓글(${id}) 업데이트 성공`);
                getAllComments(); // 댓글 목록 갱신
                commentModal.hide(); // 모달 숨김
            }    
        })
        .catch((error) => console.log(error));
        
    }
    
 });
 
 