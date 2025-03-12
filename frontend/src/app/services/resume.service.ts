import { Injectable } from '@angular/core';
    import { Resume } from "../resume";

    @Injectable({
      providedIn: 'root'
    })
    export class ResumeService {
      url = 'http://localhost:8080/api/resumes';

      constructor() { }

      async getAllResumes(): Promise<Resume[]> {
        const data = await fetch(this.url);
        return await data.json() ?? [];
      }

      async getResumeById(id: number): Promise<Resume | undefined> {
        const data = await fetch(`${this.url}/${id}`);
        return data.json() ?? {};
      }

      async createResume(resume: Resume): Promise<Resume> {
        const response = await fetch(this.url, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(resume)
        });
        return await response.json();
      }

      async updateResume(id: number, resume: Resume): Promise<Resume> {
        const response = await fetch(`${this.url}/${id}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(resume)
        });
        return await response.json();
      }

      async deleteResume(id: number): Promise<void> {
        await fetch(`${this.url}/${id}`, { method: 'DELETE' });
      }
    }
