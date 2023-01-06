module module.a {
  exports pack1;
//  exports pack2; 패키지 은닉을 통해 다른 모듈에서 module a의 pack2 패키지에 접근을 하지 못함
  requires transitive module.b; // module.a 모듈에 의존하기만 하면 module.b도 함께 의존할 수 있게 의존성 전이
  opens pack2; // 은닉된 pack2 패키지에 대해서 리플렉션 허용
}