import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Resume} from '../models/resume';

const baseUrl = 'http://localhost:8080/api/resumes';

@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  constructor(private http: HttpClient) {
  }

  showAllResumes(): Observable<Resume[]> {
    return this.http.get<Resume[]>(baseUrl);
  }

  findResumeById(id: number): Observable<Resume> {
    return this.http.get<Resume>(`${baseUrl}/${id}`);
  }

  createResume(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  updateResume(id: number, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  deleteResume(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAllResumes(): Observable<any> {
    return this.http.delete(baseUrl);
  }
}
