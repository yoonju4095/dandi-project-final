

// 캘린더
const date = new Date();

const renderCalendar = () => {
  date.setDate(1);

  const monthDays = document.querySelector(".days");

  const lastDay = new Date(
    date.getFullYear(),
    date.getMonth() + 1,
    0
  ).getDate();

  const prevLastDay = new Date(
    date.getFullYear(),
    date.getMonth(),
    0
  ).getDate();

  const firstDayIndex = date.getDay();

  const lastDayIndex = new Date(
    date.getFullYear(),
    date.getMonth() + 1,
    0
  ).getDay();

  const nextDays = 7 - lastDayIndex - 1;

  const months = [
    "1월",
    "2월",
    "3월",
    "4월",
    "5월",
    "6월",
    "7월",
    "8월",
    "9월",
    "10월",
    "11월",
    "12월",
  ];

  document.querySelector(".date h2").innerHTML = months[date.getMonth()];

  document.querySelector(".date p").innerHTML = new Date().toDateString(); //toDateString -> 문자열

  let days = "";

  for (let j = firstDayIndex; j > 0; j--) {
    days += `<div class="prev-date">${prevLastDay - j + 1}</div>`;
  }

  for (let i = 1; i <= lastDay; i++) {
    if (
      i === new Date().getDate() &&
      date.getMonth() === new Date().getMonth()
    ) {
      days += `<div class="today">${i}</div>`;
    } else {
      days += `<div class="day">${i}</div>`;
    }
  }

  for (let k = 1; k <= nextDays; k++) {
    days += `<div class="next-date">${k}</div>`;
    monthDays.innerHTML = days;
  }
};

document.querySelector(".leftBtn").addEventListener("click", () => {
  date.setMonth(date.getMonth() - 1);
  renderCalendar();
});

document.querySelector(".rightBtn").addEventListener("click", () => {
  date.setMonth(date.getMonth() + 1);
  renderCalendar();
});

renderCalendar();

const modalBox = document.getElementById("scheduleModalBox");
// const workday = document.querySelector('.days div:nth-of-type(26)')
// workday.addEventListener('click', ()=>{
//   console.log('d')
//   modalBox.showModal();
// })

//DB에 문자열 타입의 년월일을 date 타입으로 파싱하여 달력으로 출력하기

//tip-> new Date('2023-04-13') -> 문자열로 입력된 시간으로 다시 값을 담음 