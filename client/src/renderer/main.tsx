async function testIPC() {
  const res = await window.api.ping();
  console.log(res);
}
testIPC();