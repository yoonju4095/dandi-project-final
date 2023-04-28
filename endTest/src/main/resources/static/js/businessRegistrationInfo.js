import { ajax } from "/schedule/ajax.js";

//사업자상태확인
const $chkBtn = document.querySelector(".chkBtn");
$chkBtn.addEventListener("click", businessNumberChk_h, false);

//사업자 상태확인
function businessNumberChk_h(e) {
  const submitBtn = document.querySelector(".submitBtn");
  const key =
    "bJ0AcEWnYARdHMe24EsPd77ralP%2BiRWLuhIeWgoIBgM%2F4dqlAgbS%2FilwgSiZkbkL9ojCBQHuEZI2TtoMqYzRhA%3D%3D";

  const returnType = "JSON";

  const url = `http://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=${key}&returnType=${returnType}`;
  const businessNm = document.getElementById("businessCompany");
  //const payLoad = JSON.parse(`{"b_no": [${businessNm.value} ] }`);
  //const payLoad = { "b_no": [businessNm.value] };
  const payLoad = { b_no: [businessNm.value] };
  //1)상태결과
  const businessStatusChk = (res) => {
    console.log(res);
    if (res.status_code == "OK") {
      switch (
        res.data[0].b_stt_cd //납세자 상태
      ) {
        case "01": //계속사업자
          console.log("계속");
          alert("인증되었습니다.");
          // 회원가입 버튼 활성화
          submitBtn.disabled = false;
          break;
        case "02": // 휴업자
        case "03": // 폐업자
        default:
          // 회원가입 버튼 비활성화
          submitBtn.disabled = true;
          alert("국세청에 등록되지 않은 사업자등록번호입니다.");
          throw new Error(`${res.data[0].tax_type}`);
      }
    } else {
      // 회원가입 버튼 비활성화
      submitBtn.disabled = true;
      throw new Error(`${res.description}`);
    }
  };
  ajax
    .post(url, payLoad)
    .then((res) => res.json())
    .then((res) => businessStatusChk(res))
    .catch((err) => console.log(err.message));
}
