import { app, BrowserWindow, ipcMain } from 'electron';
import path from 'node:path';

const createWindow = () => {
  const win = new BrowserWindow({
    width: 1000,
    height: 700,
    webPreferences: {
      preload: path.join(__dirname, '../preload/index.js'),
      contextIsolation: true,
      nodeIntegration: false,
    },
  });

  if (process.env.ELECTRON_RENDERER_URL) {
    win.loadURL(process.env.ELECTRON_RENDERER_URL);
  } else {
    win.loadFile(path.join(__dirname, '../renderer/index.html'));
  }
};

app.whenReady().then(createWindow);
ipcMain.handle('ping', () => `pong (Electron ${process.versions.electron})`);

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit();
});
