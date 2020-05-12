import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalserviceService {

  showmenu: boolean = false;
  baseUrl = 'http://localhost:8082/api';
  constructor() { }


  setShowMenu(showornot: boolean): void {
    this.showmenu = showornot;
  }

  getShowMenu(): boolean {
    return this.showmenu;
  }
}
