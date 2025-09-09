export {}; // 이 파일을 '모듈'로 만들어서 전역 보강 허용

declare global {
  interface Window {
    api: {
      ping(): Promise<string>;
      // 여기에 필요한 IPC API 시그니처를 계속 추가
    };
  }
}