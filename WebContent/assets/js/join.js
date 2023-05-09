/**
 * 
 */

let $checkMsg = $('#check-id-msg');
let $checkPwMsg = $('#check-pw-msg');

let $idInput = $('#id');
let $pwInput = $('#password');

$idInput.on('blur', function() {
	let id = $(this).val();
	
	if(id == ''){
		$checkMsg.text('아이디를 입력하세요!');
	}else{
	
	$.ajax({
		url : 'checkIdOk.me',
		type : 'get',
		data : {memberId : id},
		success : function(result){
			/*console.log(result);*/
			$checkMsg.text(result);
		}
		
	});
	
	}
});


/*영어, 숫자, 특수문자로 이루어진 비밀번호 8글자 이상
 	영어 대소문자를 구분하지 않음*/
const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\d!@#$%^&*()_+]{8,}$/;

// 비밀번호 입력확인하기
$pwInput.on('blur', function(){
	if(regex.test( $(this).val() )){
		$checkPwMsg.text("사용 가능한 비밀번호 입니다.");
	}else{
		$checkPwMsg.html("사용 불가능한 비밀번호입니다.<br>영어, 숫자, 특수문자를 포함한 8글자 이상으로 작성해주세요.")
	}
	
});

// 약관 확인하기
$('form').on('submit', function(e){
	e.preventDefault(); // 기본 이벤트를 막아주는 명령어이다.
	
	console.log( $('#agree').prop('checked') );
	if( $('#agree').prop('checked') ){
		this.submit(); // submit 이벤트를 발생시키는 메소드(당연히 폼 요소에 사용해야한다.)
	}else{
		alert("약관에 동의해주세요!")
	}
	
});






