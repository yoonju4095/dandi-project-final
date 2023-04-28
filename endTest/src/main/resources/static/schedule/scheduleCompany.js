import { ajax } from "/schedule/ajax.js";

const $scheduleFields = {
  scheduleIdPk,
  myId,
  nameSchedule,
  idSchedule,
  workSchedule,
  daySchedule,
  comeInSchedule,
  comeOutSchedule,
  periodStart,
  periodEnd,
};

const $allBtn = {
  add: document.querySelector(".addBtn"),
  manage: document.querySelector(".manageBtn"),
  set: document.querySelector(".setBtn"),
  inquiry: document.querySelector(".inquiryBtn"),
  update: document.querySelector(".updateBtn"),
  del: document.querySelector(".delBtn"),
};

const $noId = document.querySelector(".noId");
const $titleNew = document.querySelector(".title_new");
const $titleManage = document.querySelector(".title_manage");

// 목록
const listRow = (p) =>
  `
  <div>
    <div>
      <div class="box">${p.scheduleIdPk}</div>
    </div>
    <div class="item">
      <i class="fa-regular fa-user"></i>
      <div style="display: none;">${p.myId}</div>
      <div class="box">${p.nameSchedule}</div>
      <div class="box">${p.idSchedule}</div>
    </div>
    <div>
      <div class="box">${p.workSchedule}</div>
    </div>
    <div>
      <div class="box">${p.daySchedule}</div>
    </div>
    <div class="item">
      출근 <div class="box">${p.comeInSchedule}</div>
      퇴근 <div class="box">${p.comeOutSchedule}</div>
    </div>
    <div class="item">
      <div class="box">${p.periodStart}</div>
      <div class="box">${p.periodEnd}</div>
    </div>
  </div>
`;

//신규 등록 -> 입력 창 생성
const $inputPopup = document.querySelector(".inputPopup");
const newForm = () => {
  $inputPopup.showModal();
  $titleNew.classList.remove("off");
  $titleManage.classList.add("off");
  $allBtn.set.classList.remove("off");
  $allBtn.inquiry.classList.add("off");
  $boxInput.classList.remove("off");
  $allBtn.update.classList.add("off");
  $allBtn.del.classList.add("off");
  $noId.classList.add("off");
  $inputPopup.addEventListener("close", (e) => {
    if ($inputPopup.returnValue != "set") {
      return;
    } else {
    }
  });
};
$allBtn.add.addEventListener("click", newForm);

//목록 기능
const list = (res) => {
  if (res.header.rtcd == "00") {
    const html = res.data.map((p) => listRow(p)).join("");
    document.querySelector(".main__scheduleTable .main__tableBody").innerHTML =
      html;
  }
};

const list_h = (id) => {
  const url = `http://localhost:9080/api/schedules/managing/${id}`;
  ajax
    .get(url)
    .then((res) => res.json())
    .then(list)
    .catch(console.error);
};

//등록
const add = (res) => {
  if (res.header.rtcd == "00") {
    document.querySelector("form").reset();
    list_h(myId.value);
  } else {
  }
};

const add_h = (e) => {
  //요일 체크박스 등록 처리
  const $dayChk = document.querySelectorAll(".daySchedule input[name=chk]");
  const chks = Array.from($dayChk)
    .filter((input) => input.checked)
    .map((input) => input.value);

  const url = `http://localhost:9080/api/schedules`;
  const payload = {
    myId: $scheduleFields.myId.value,
    nameSchedule: $scheduleFields.nameSchedule.value,
    idSchedule: $scheduleFields.idSchedule.value,
    workSchedule: $scheduleFields.workSchedule.value,
    daySchedule: $scheduleFields.daySchedule.value,
    comeInSchedule: $scheduleFields.comeInSchedule.value,
    comeOutSchedule: $scheduleFields.comeOutSchedule.value,
    periodStart: $scheduleFields.periodStart.value,
    periodEnd: $scheduleFields.periodEnd.value,
  };
  ajax
    .post(url, payload)
    .then((res) => res.json())
    .then(add)
    .catch(console.error);
};
$allBtn.set.addEventListener("click", add_h);

//관리창
const $boxInput = document.querySelector(".box_input");
$allBtn.manage.addEventListener("click", (e) => {
  newForm();
  $titleNew.classList.add("off");
  $titleManage.classList.remove("off");
  $allBtn.set.classList.add("off");
  $noId.classList.remove("off");
  $allBtn.inquiry.classList.remove("off");
  $boxInput.classList.add("off");
});

//수정1) 조회
const inquiry = (res) => {
  if (res.header.rtcd == "00") {
    $scheduleFields.scheduleIdPk.value = res.data.scheduleIdPk;
    $scheduleFields.nameSchedule.value = res.data.nameSchedule;
    $scheduleFields.idSchedule.value = res.data.idSchedule;
    $scheduleFields.workSchedule.value = res.data.workSchedule;
    $scheduleFields.daySchedule.value = res.data.daySchedule;
    $scheduleFields.comeInSchedule.value = res.data.comeInSchedule;
    $scheduleFields.comeOutSchedule.value = res.data.comeOutSchedule;
    $scheduleFields.periodStart.value = res.data.periodStart;
    $scheduleFields.periodEnd.value = res.data.periodEnd;
  } else if (res.header.rtcd == "99") {
    return;
  }
};

const inquiry_h = (id) => {
  const url = `http://localhost:9080/api/schedules/${id}`;
  ajax
    .get(url)
    .then((res) => res.json())
    .then(inquiry)
    .catch(console.error);
};
$allBtn.inquiry.addEventListener("click", (e) => {
  if ($noId.value === "") {
    return;
  } else {
    if ($boxInput.classList.contains("off")) {
      $boxInput.classList.remove("off");
      $allBtn.update.classList.remove("off");
      $allBtn.del.classList.remove("off");
    }
  }
  const id = $scheduleFields.scheduleIdPk.value.trim();
  inquiry_h(id);
});

//수정2) 수정 처리
const modify = (res) => {
  if (res.header.rtcd == "00") {
    list_h(myId.value);
  } else {
  }
};

const modify_h = (e, id) => {
  const url = `http://localhost:9080/api/schedules/${id}`;
  const payload = {
    nameSchedule: $scheduleFields.nameSchedule.value,
    idSchedule: $scheduleFields.idSchedule.value,
    workSchedule: $scheduleFields.workSchedule.value,
    daySchedule: $scheduleFields.daySchedule.value,
    comeInSchedule: $scheduleFields.comeInSchedule.value,
    comeOutSchedule: $scheduleFields.comeOutSchedule.value,
    periodStart: $scheduleFields.periodStart.value,
    periodEnd: $scheduleFields.periodEnd.value,
  };
  ajax
    .patch(url, payload)
    .then((res) => res.json())
    .then(modify)
    .catch(console.error);
};
$allBtn.update.addEventListener("click", (e) => {
  const id = $scheduleFields.scheduleIdPk.value.trim();
  modify_h(e, id);
});

//삭제
const del = (res) => {
  if (res.header.rtcd == "00") {
    list_h(myId.value);
  }
};

const del_h = (id) => {
  const url = `http://localhost:9080/api/schedules/${id}`;
  ajax
    .delete(url)
    .then((res) => res.json())
    .then(del)
    .catch(console.error);
};
$allBtn.del.addEventListener("click", (e) => {
  const id = $scheduleFields.scheduleIdPk.value.trim();
  if (id) {
    confirm("삭제하시겠습니까?");
    del_h(id);
  }
});

//목록 -> 화면 처리
list_h(myId.value);
