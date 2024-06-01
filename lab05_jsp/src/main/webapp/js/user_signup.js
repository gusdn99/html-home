/**
 * /user/signup.jsp에 포함.
 */

// HTML DOM(Document Object Model) 컨텐트 로딩이 끝났을 때, 이벤트 리스너를 실행.
document.addEventListener('DOMContentLoaded', () => {

    const inputUserid = document.querySelector('input#userid');
    const btnCheckDuplicateUserId = document.querySelector('button#btnCheckDuplicateUserId');

    const inputPassword = document.querySelector('input#password');

    const inputEmail = document.querySelector('input#email');
    const btnCheckDuplicateEmail = document.querySelector('button#btnCheckDuplicateEmail');

    const btnSignUp = document.querySelector('button#btnSignUp');

    const btnCancel = document.querySelector('button#btnCancel');

    btnCheckDuplicateUserId.addEventListener('click', () => {
        const userid = inputUserid.value;
        if (userid === "") {
            alert("아이디를 입력하세요!");
            return;
        }
        
        // 중복 확인 로직 구현 (예: AJAX 요청)
        fetch(`/check-duplicate-userid?userid=${encodeURIComponent(userid)}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    alert("이미 사용중인 아이디입니다.");
                } else {
                    alert("사용 가능한 아이디입니다.");
                }
            })
            .catch(error => {
                console.error("Error checking duplicate userid:", error);
                alert("아이디 중복 확인 중 오류가 발생했습니다.");
            });

    });

    btnCheckDuplicateEmail.addEventListener('click', () => {
        const email = inputEmail.value.trim();
        if (email === "") {
            alert("이메일을 입력하세요!");
            return;
        }

        // 중복 확인 로직 구현 (예: AJAX 요청)
        fetch(`/check-duplicate-email?email=${encodeURIComponent(email)}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    alert("이미 사용중인 이메일입니다.");
                } else {
                    alert("사용 가능한 이메일입니다.");
                }
            })
            .catch(error => {
                console.error("Error checking duplicate email:", error);
                alert("이메일 중복 확인 중 오류가 발생했습니다.");
            });
        

    });


    btnCancel.addEventListener('click', () => {
        location.href = `/lab05/`;
    });



});