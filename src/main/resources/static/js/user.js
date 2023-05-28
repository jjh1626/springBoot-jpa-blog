let index = {
    init: function(){
        $("#btnSave").on("click",()=>{  //function(){} 을 ()=>{} 로 사용한 이유는 this를 바인딩하기 위해서!!
            //alert('여기');
            this.save();
        });
    },
    save: function(){
        //alert('user의 save함수 호출');
        //let formData = $("#joinForm").serialize();    //key=value&key=value&... 형태
        let formData = {                                //자바스크립트 객체 형태
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        console.log(formData);

        //ajax 통신을 이용해서 데이터를 json으로 변경하여 insert 요청
        //ajax 호출시 default 가 비동기 호출
        $.ajax({
            //회원가입 수행 요청
            type: "POST",
            url: "/api/user",
            data: JSON.stringify(formData), //http body 데이터 (자바스크립트 객체를 JSON 형태로 변환)
            contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            dataType: "json"  //서버에서 응답이 왔을때 json 형태의 문자열을 자바스크립트 객체로 변환해 준다.
        }).done(function(result){
            console.log(result);
            if(result.status == '200'){
                 //성공시
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            } else {
                alert("회원가입 실패!!");
            }
        }).fail(function(error){
            //실패시
            alert(JSON.stringify(error));
        });

        // $.ajax({
        //     url:"/user/join",
        //     type:"POST",
        //     data:formData,
        //     success:function(data){
        //         let jsonObj = JSON.parse(data);
        //     },
        //     error:function(xhr,status){
        //         alert(xhr + ":"+status);
        //     }
        // });
    }
}

index.init();