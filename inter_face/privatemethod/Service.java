package inter_face.privatemethod;
/*
 * private 메서드: default 메서드에서만 호출 가능
 * 
 * private 정적 메서드: default 메서드, 정적 메서드에서 모두 호출 가능
 * 
 * default, 정적 메서드 내에서 중복을 제거하기 위해서 제공
 */
public interface Service {
    default void defaultMethod1() {
        System.out.println("defaultMethod1 호출");
        defaultCommon();
    }

    default void defaultMethod2() {
        System.out.println("defaultMethod2 호출");
        defaultCommon();
        staticCommon();// default 메서드에서도 private 정적 메서드 호출
    }

    private void defaultCommon() {
        System.out.println("defaultMethod 중복 코드 호출");
    }

    static void staticMethod1() {
        System.out.println("staticMethod1 호출");
        staticCommon();
    }

    static void staticMethod2() {
        System.out.println("staticMethod2 호출");
        staticCommon();
    }

    private static void staticCommon() {
        System.out.println("staticMethod 중복 코드 호출");
    }
}
