const toggle1 = document.querySelector('#gnb__box .gnb__info');
const container1 = document.querySelector('.container1');
toggle1?.addEventListener('click', (e) => {
  container1.classList.toggle('on');
  toggle1.classList.toggle('on');
});

const toggle2 = document.querySelector('#gnb__box .gnb__cum');
const container2 = document.querySelector('.container2');
toggle2?.addEventListener('click', (e) => {
  container2.classList.toggle('on');
  toggle2.classList.toggle('on');
});

const toggle3 = document.querySelector('#gnb__box .gnb__member');
const container3 = document.querySelector('.container3');
toggle3?.addEventListener('click', (e) => {
  container3.classList.toggle('on');
  toggle3.classList.toggle('on');
});

const toggle4 = document.querySelector('#gnb__box .gnb__manager');
const container4 = document.querySelector('.container4');
toggle4?.addEventListener('click', (e) => {
  container4.classList.toggle('on');
  toggle4.classList.toggle('on');
});

const toggle5 = document.querySelector('#gnb__box .gnb__support');
const container5 = document.querySelector('.container5');
toggle5?.addEventListener('click', (e) => {
  container5.classList.toggle('on');
  toggle5.classList.toggle('on');
});
const $resumeModalBox = document.getElementById('resumeModalBox');
const $resumeBtn = document.getElementById('resumeBtn');
      const $resumeModal = document.getElementById('resumeModal');
      const $resumeBtnCancel = document.getElementById('modalCancel');

     $resumeBtn?.addEventListener('click', e => {
     $resumeModalBox.style.display = 'block';
    $resumeModal.style.display = 'block';
     });

     $resumeBtnCancel?.addEventListener('click', e=> {
     $resumeModal.style.display = 'none';
     $resumeModalBox.style.display = 'none';
     });

      const $resumeMove = document.getElementById('resumeMove');
    $resumeMove?.addEventListener('click', e => {
        alert('지원하셨습니다');
    });