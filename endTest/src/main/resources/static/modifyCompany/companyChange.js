//이벤트 타겟 설정
const $menu1 = document.querySelector(".main__menu1");
const $menu2 = document.querySelector(".main__menu2");
const $menu3 = document.querySelector(".main__menu3");
const $informationChange = document.querySelector(".mainContentMenu1");
const $passwordChange = document.querySelector(".mainContentMenu2");
const $unregisterChange = document.querySelector(".mainContentMenu3");

//토글 기능
// const changeInformation = () => {
//   if (!$informationChange.classList.contains('active')) {
//     // $informationChange.classList.toggle('active');
//     $passwordChange.classList.toggle('active');
//     $unregisterChange.classList.toggle('active');
//   }
//   if ($menu1.classList.contains('active')) {
//     $menu1.classList.toggle('active');
//     $menu2.classList.toggle('active');
//     $menu3.classList.toggle('active');
//   }
// };

const changeInformation = () => {
  if ($informationChange.classList.contains("active")) {
    $informationChange.classList.toggle("active");
    $passwordChange.classList.add("active");
    $unregisterChange.classList.add("active");
  }

  if (!$menu1.classList.contains("active")) {
    $menu2.classList.toggle("active");
    $menu3.classList.toggle("active");
  }
};
//이벤트 리스너 설정
$menu1.addEventListener("click", changeInformation);
$menu1.addEventListener("click", (e) => {
  $menu1.style.backgroundColor = "#00b3a8";
  $menu2.style.backgroundColor = "#79cd9c";
  $menu3.style.backgroundColor = "#79cd9c";
});

//토글 기능 -> 기업회원으로 전환하는 로직
const changePassword = () => {
  if ($passwordChange.classList.contains("active")) {
    $passwordChange.classList.toggle("active");
    $informationChange.classList.add("active");
    $unregisterChange.classList.add("active");
  }
  if (!$menu2.classList.contains("active")) {
    $menu1.classList.toggle("active");
    $menu3.classList.toggle("active");
  }
};
//이벤트 리스너 설정
$menu2.addEventListener("click", changePassword);
$menu2.addEventListener("click", (e) => {
  $menu2.style.backgroundColor = "#00b3a8";
  $menu3.style.backgroundColor = "#79cd9c";
  $menu1.style.backgroundColor = "#79cd9c";
});

const changeUnregister = () => {
  if ($unregisterChange.classList.contains("active")) {
    $unregisterChange.classList.toggle("active");
    $informationChange.classList.add("active");
    $passwordChange.classList.add("active");
  }
  if (!$menu2.classList.contains("active")) {
    $menu1.classList.toggle("active");
    $menu3.classList.toggle("active");
  }
};
//이벤트 리스너 설정
$menu3.addEventListener("click", changeUnregister);
$menu3.addEventListener("click", (e) => {
  $menu3.style.backgroundColor = "#00b3a8";
  $menu2.style.backgroundColor = "#79cd9c";
  $menu1.style.backgroundColor = "#79cd9c";
});

function sample6_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
            extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("sample6_extraAddress").value = extraAddr;
      } else {
        document.getElementById("sample6_extraAddress").value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("sample6_postcode").value = data.zonecode;
      document.getElementById("sample6_address").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("sample6_detailAddress").focus();
    },
  }).open();
}

/* 비밀번호 공개 비공개 유무*/
{
  const $currentPW = document.getElementById("currentPW");
  const $faCircleCheck1_1 = document.querySelector(".fa-solid.fa-circle-check");
  const $faCircleCheck2_1 = document.querySelector(
    ".fa-regular.fa-circle-check"
  );

  $faCircleCheck1_1.addEventListener("click", () => {
    console.log("click");
    $currentPW.setAttribute("type", "text");
    // $currentPW.style.width = '56%';
    $faCircleCheck1_1.style.display = "none";
    $faCircleCheck2_1.style.display = "inline-block";
  });
  $faCircleCheck2_1.addEventListener("click", () => {
    $currentPW.setAttribute("type", "password");
    $faCircleCheck2_1.style.display = "none";
    $faCircleCheck1_1.style.display = "inline-block";
  });
}

{
  const $newPW2 = document.getElementById("newPW2");
  const $faCircleCheck1_2 = document.querySelector(
    ".fa-solid.fa-circle-check.a"
  );
  const $faCircleCheck2_2 = document.querySelector(
    ".fa-regular.fa-circle-check.a"
  );

  $faCircleCheck1_2.addEventListener("click", () => {
    console.log("click");
    $newPW2.setAttribute("type", "text");
    // $newPW2.style.width = '56%';
    $faCircleCheck1_2.style.display = "none";
    $faCircleCheck2_2.style.display = "inline-block";
  });
  $faCircleCheck2_2.addEventListener("click", () => {
    $newPW2.setAttribute("type", "password");
    $faCircleCheck2_2.style.display = "none";
    $faCircleCheck1_2.style.display = "inline-block";
  });
}

{
  const $newPW3 = document.getElementById("newPW3");
  const $faCircleCheck1_3 = document.querySelector(
    ".fa-solid.fa-circle-check.b"
  );
  const $faCircleCheck2_3 = document.querySelector(
    ".fa-regular.fa-circle-check.b"
  );

  $faCircleCheck1_3.addEventListener("click", () => {
    console.log("click");
    $newPW3.setAttribute("type", "text");
    // $newPW3.style.width = '56%';
    $faCircleCheck1_3.style.display = "none";
    $faCircleCheck2_3.style.display = "inline-block";
  });
  $faCircleCheck2_3.addEventListener("click", () => {
    $newPW3.setAttribute("type", "password");
    $faCircleCheck2_3.style.display = "none";
    $faCircleCheck1_3.style.display = "inline-block";
  });
}

{
  const $UR2_1 = document.getElementById("UR2_1");
  const $faCircleCheck1_4 = document.querySelector(
    ".fa-solid.fa-circle-check.c"
  );
  const $faCircleCheck2_4 = document.querySelector(
    ".fa-regular.fa-circle-check.c"
  );

  $faCircleCheck1_4.addEventListener("click", () => {
    console.log("click");
    $UR2_1.setAttribute("type", "text");
    // $UR2_1.style.width = '56%';
    $faCircleCheck1_4.style.display = "none";
    $faCircleCheck2_4.style.display = "inline-block";
  });
  $faCircleCheck2_4.addEventListener("click", () => {
    $UR2_1.setAttribute("type", "password");
    $faCircleCheck2_4.style.display = "none";
    $faCircleCheck1_4.style.display = "inline-block";
  });
}
{
  const $UR3_1 = document.getElementById("UR3_1");
  const $faCircleCheck1_5 = document.querySelector(
    ".fa-solid.fa-circle-check.d"
  );
  const $faCircleCheck2_5 = document.querySelector(
    ".fa-regular.fa-circle-check.d"
  );

  $faCircleCheck1_5.addEventListener("click", () => {
    console.log("click");
    $UR3_1.setAttribute("type", "text");
    // $UR3_1.style.width = '56%';
    $faCircleCheck1_5.style.display = "none";
    $faCircleCheck2_5.style.display = "inline-block";
  });
  $faCircleCheck2_5.addEventListener("click", () => {
    $UR3_1.setAttribute("type", "password");
    $faCircleCheck2_5.style.display = "none";
    $faCircleCheck1_5.style.display = "inline-block";
  });
}

// // 공백 사용 못 하게 이메일 특수문자사용
// function noSpaceForm(obj) {
//   var str_space = /\s/; // 공백 체크
//   if (str_space.exec(obj.value)) {
//     // 공백 체크
//     obj.focus();
//     obj.value = obj.value.replace(' ', ''); // 공백제거
//     return false;
//   }
// }

// // 특수문자와 공백 삭제 처리 - 아이디 이름 이메일빼고 전부
// function characterCheck(obj) {
//   var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
//   if (regExp.test(obj.value)) {
//     obj.value = obj.value.substring(0, obj.value.length - 1); // 입력한 특수문자 한자리 지움
//   }
// }

// //영어 숫자만 받아옴 - 비밀번호류
// function onlyAlpEnNum(e) {
//   e.value = e.value.replace(
//     /[^A-Za-z0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi,
//     '',
//   );
// }

// //숫자만 입력가능 사업자등록번호 휴대폰번호
// function onlyAlpNum(e) {
//   e.value = e.value.replace(/[^0-9]/gi, '');
// }

// function checkInformationChange() {
//   if (!checkEmail(form.email.value)) {
//     return false;
//   } else if (!checkPhone(form.phone.value)) {
//     return false;
//   }
//   return true;
// }

{
  //이메일 유효성 검사
  const $emailWrite = document.getElementById("email");
  const $email_down = document.getElementById("email_down");
  const $emailCheck = /[ ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $emailChecks = /[ \{\}\[\]\/?,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\\(\=\s]/gi;
  $emailWrite.addEventListener("keyup", (e) => {
    if (
      (e.target.value.trim().length >= 10 &&
        e.target.value.trim().length < 30 &&
        !$emailCheck.test($emailWrite.value)) ||
      e.target.value.trim().length == 0
    ) {
      $email_down.style.display = "none";
    } else {
      $email_down.style.display = "block";
    }
    if ($emailChecks.test($emailWrite.value)) {
      $emailWrite.value = $emailWrite.value.substring(
        0,
        $emailWrite.value.length - 1
      ); // 입력한 특수문자 한자리 지움
    }
  });
}

{
  //휴대전화 유효성 검사
  const $phoneWrite = document.getElementById("phone");
  const $phone_down = document.getElementById("phone_down");
  const $phoneCheck = /[ A-Za-zㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $phoneChecks = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
  $phoneWrite.addEventListener("keyup", (e) => {
    if (
      (e.target.value.trim().length >= 11 &&
        e.target.value.trim().length <= 12 &&
        !$phoneCheck.test($phoneWrite.value)) ||
      e.target.value.trim().length == 0
    ) {
      $phone_down.style.display = "none";
    } else {
      $phone_down.style.display = "block";
    }
    if ($phoneChecks.test($phoneWrite.value)) {
      $phoneWrite.value = $phoneWrite.value.substring(
        0,
        $phoneWrite.value.length - 1
      ); // 입력한 특수문자 한자리 지움
    }
  });
}

{
  //사업자등록번호 유효성 검사
  const $companyNumberWrite = document.getElementById("businessCompany");
  const $companyNumber_down = document.getElementById("companyNumber_down");
  const $companyNumberCheck = /[ A-Za-zㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $companyNumberChecks =
    /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
  $companyNumberWrite.addEventListener("keyup", (e) => {
    if (
      (e.target.value.trim().length >= 10 &&
        e.target.value.trim().length <= 11 &&
        !$companyNumberCheck.test($companyNumberWrite.value)) ||
      e.target.value.trim().length == 0
    ) {
      $companyNumber_down.style.display = "none";
    } else {
      $companyNumber_down.style.display = "block";
    }
    if ($companyNumberChecks.test($companyNumberWrite.value)) {
      $companyNumberWrite.value = $companyNumberWrite.value.substring(
        0,
        $companyNumberWrite.value.length - 1
      ); // 입력한 특수문자 한자리 지움
    }
  });
}

{
  //비밀번호 유효성 검사
  const $currentPWWrite = document.getElementById("currentPW");
  const $currentPW_down = document.getElementById("currentPW_down");
  const $currentPWCheck = /[ ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $currentPWCheck2 =
    /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
  const $currentPWChecks = /\s/;
  $currentPWWrite.addEventListener("keyup", (e) => {
    if (
      (e.target.value.trim().length >= 8 &&
        e.target.value.trim().length <= 20 &&
        !$currentPWCheck.test($currentPWWrite.value) &&
        $currentPWCheck2.test($currentPWWrite.value)) ||
      e.target.value.trim().length == 0
    ) {
      $currentPW_down.style.display = "none";
    } else {
      $currentPW_down.style.display = "block";
    }
    if ($currentPWChecks.test($currentPWWrite.value)) {
      $currentPWWrite.value = $currentPWWrite.value.substring(
        0,
        $companyNumberWrite.value.length - 1
      ); // 공백일경우
    }
  });
}

{
  //비밀번호 유효성 검사
  const $newPW2Write = document.getElementById("newPW2");
  const $newPW2_down = document.getElementById("newPW2_down");
  const $newPW2Check = /[ ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $newPW2Check2 =
    /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
  const $newPW2Checks = /\s/;
  $newPW2Write.addEventListener("keyup", (e) => {
    if (
      (e.target.value.trim().length >= 8 &&
        e.target.value.trim().length <= 20 &&
        !$newPW2Check.test($newPW2Write.value) &&
        $newPW2Check2.test($newPW2Write.value)) ||
      e.target.value.trim().length == 0
    ) {
      $newPW2_down.style.display = "none";
    } else {
      $newPW2_down.style.display = "block";
    }
    if ($newPW2Checks.test($newPW2Write.value)) {
      $newPW2Write.value = $newPW2Write.value.substring(
        0,
        $newPW2Write.value.length - 1
      ); // 공백일 경우
    }
  });
}

{
  //비밀번호 유효성 검사
  const $newPW3Write = document.getElementById("newPW3");
  const $newPW3_down = document.getElementById("newPW3_down");
  const $newPW3Check = /[ ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $newPW3Check2 =
    /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
  const $newPW3Checks = /\s/;
  $newPW3Write.addEventListener("keyup", (e) => {
    if (
      (e.target.value.trim().length >= 8 &&
        e.target.value.trim().length <= 20 &&
        !$newPW3Check.test($newPW3Write.value) &&
        $newPW3Check2.test($newPW3Write.value)) ||
      e.target.value.trim().length == 0
    ) {
      $newPW3_down.style.display = "none";
    } else {
      $newPW3_down.style.display = "block";
    }
    if ($newPW3Checks.test($newPW3Write.value)) {
      $newPW3Write.value = $newPW3Write.value.substring(
        0,
        $newPW3Write.value.length - 1
      ); // 공백일 경우
    }
  });
}

{
  //비밀번호 유효성 검사
  const $UR2_1Write = document.getElementById("UR2_1");
  const $UR2_1_down = document.getElementById("UR2_1_down");
  const $UR2_1Check = /[ ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $UR2_1Check2 =
    /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
  const $UR2_1Checks = /\s/;
  $UR2_1Write.addEventListener("keyup", (e) => {
    if (
      (e.target.value.trim().length >= 8 &&
        e.target.value.trim().length <= 20 &&
        !$UR2_1Check.test($UR2_1Write.value) &&
        $UR2_1Check2.test($UR2_1Write.value)) ||
      e.target.value.trim().length == 0
    ) {
      $UR2_1_down.style.display = "none";
    } else {
      $UR2_1_down.style.display = "block";
    }
    if ($UR2_1Checks.test($UR2_1Write.value)) {
      $UR2_1Write.value = $UR2_1Write.value.substring(
        0,
        $UR2_1Write.value.length - 1
      ); // 공백일 경우
    }
  });
}

{
  //비밀번호 유효성 검사
  const $UR3_1Write = document.getElementById("UR3_1");
  const $UR3_1_down = document.getElementById("UR3_1_down");
  const $UR3_1Check = /[ ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $UR3_1Check2 =
    /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
  const $UR3_1Checks = /\s/;
  $UR3_1Write.addEventListener("keyup", (e) => {
    if (
      (e.target.value.trim().length >= 8 &&
        e.target.value.trim().length <= 20 &&
        !$UR3_1Check.test($UR3_1Write.value) &&
        $UR3_1Check2.test($UR3_1Write.value)) ||
      e.target.value.trim().length == 0
    ) {
      $UR3_1_down.style.display = "none";
    } else {
      $UR3_1_down.style.display = "block";
    }
    if ($UR3_1Checks.test($UR3_1Write.value)) {
      $UR3_1Write.value = $UR3_1Write.value.substring(
        0,
        $UR3_1Write.value.length - 1
      ); // 공백일 경우
    }
  });
}
