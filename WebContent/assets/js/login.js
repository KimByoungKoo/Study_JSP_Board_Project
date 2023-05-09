/**
 * 
 */
// js로 req객체에 직접 접근하는 것은 불가능하다.
// 그러나 브라우저 주소창에 있는 쿼리스트링을 가져와 쓸 수 있다.
// window -> 브라우저 객체
// location -> 주소창 객체
 console.log(window.location.search);
 console.log(location.search);
let queryString = location.search;

// URL SearchParams 객체는 쿼리스트링을 해석해준다.
let urlParams = new URLSearchParams(queryString);

let result = urlParams.get('result');

if(result == 'false'){
	alert('아이디와 비밀번호를 확인해주세요.');
}