import { Injectable } from '@angular/core';
import {Resume} from "../resume";

@Injectable({
  providedIn: 'root'
})
export class ResumeService {
  protected resumeList: Resume[] = [
      {
    "id": 1,
    "name": "Antonio Ródenas",
    "email": "anrodenasj@gmail.com",
    "phone": "1234567890",
    "address": "Calle Falsa 1",
    "position": "Developer"
  }
    ]
  constructor() { }
  getAllResumes(): Resume[] {
    return this.resumeList;
  }

  getResumeById(id: number): Resume | undefined {
    return this.resumeList.find(resume => resume.id === id);
  }
}
