function getDateRangeData(start, end) {
  const resDay = [];
  let startDay = new Date(start);
  let endDay = new Date(end);

  while (startDay.getTime() <= endDay.getTime()) {
    let month = startDay.getMonth() + 1;
    month = month < 10 ? "0" + month : month;
    let day = startDay.getDate();
    day = day < 10 ? "0" + day : day;
    resDay.push(startDay.getFullYear() + "-" + month + "-" + day);
    day.setDate(day.getDate() + 1);
  }
  return resDay;
}
