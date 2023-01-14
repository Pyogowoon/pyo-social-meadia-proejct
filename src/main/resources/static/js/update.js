// (1) 회원정보 수정
function update(userId,event) {
         event.preventDefault(); //폼 태그 액션 막기

        let data=$("#profileUpdate").serialize(); // key=value 형태



        $.ajax({

         type:"put",
         url:`/api/user/${userId}/`,
         data : data,
         contentType : "application/x-www-form-urlencoded; charset=utf-8",
         dataType: "json"

        }).done(res=>{ //Httpstatus 200번대일때 done이됨
             alert("수정에 성공하였습니다.");
             location.href=`/user/${userId}`;
        }).fail(error=>{ //Httpstatus 상태코드가 200번대가 아닐때 fail
            if(error.data == null){
                alert(error.responseJSON.message);
            }else{
              alert(JSON.stringify(error.responseJSON.data));
            }

        });

 }