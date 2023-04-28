package com.back.endTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //응답시간 계산
//    registry.addInterceptor(new MeasuringInterceptor())
//      .order(1)
//      .addPathPatterns("/**")
//      .excludePathPatterns(
//        "/css/*",
//        "/js/*",
//        "/img/*",
//        "/error/*"
//      );
//    //세션 체크
//    registry.addInterceptor(new LoginCheckInterceptor())
//      .order(2)         //인터페이스 실행순서 지정
//      .addPathPatterns("/**")   //인터셉터에 포함시키는 URL 패턴, 루트경로부터 하위경로 모두 포함
//      .excludePathPatterns(
//        "/",
//        "/login",
//        "/logout",
//        "/singUpSelect",
//        "/memberPersons/singUp",
//        "/memberCompanies/singUp",
//        "/member/singUpSuccess",
//        "/css/*",
//        "/js/*",
//        "/img/*",
//        "/error/*"
//      ); //인터셉터에 제외되는 URL 패턴 지정
  }
}
