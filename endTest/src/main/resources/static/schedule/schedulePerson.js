import { ajax } from "/schedule/ajax.js";

// 목록
const listRow = (p) =>
  `
  <div>
    <div class="item">
      <i class="fa-regular fa-user"></i>
      <div style="display: none;">${p.idSchedule}</div>
      <div class="box">${p.myId}</div>
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

//목록 기능
const list = (res) => {
  if (res.header.rtcd == "00") {
    const html = res.data.map((p) => listRow(p)).join("");
    document.querySelector(".main__scheduleTable .main__tableBody").innerHTML =
      html;
  }
};

const list_h = (id) => {
  const url = `http://localhost:9080/api/schedules/viewSchedule/${id}`;
  ajax
    .get(url)
    .then((res) => res.json())
    .then(list)
    .catch(console.error);
};

const url = document.location.pathname.split("/");
const id = url[4];
//목록 -> 화면 처리
list_h(id);
