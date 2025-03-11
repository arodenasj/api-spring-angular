import {Injectable} from '@angular/core';
import {Resume} from "../resume";

@Injectable({
    providedIn: 'root'
})
export class ResumeService {
    url = 'http://localhost:8080/api/resumes';

    constructor() {
    }

    async getAllResumes(): Promise<Resume[]> {
        const data = await fetch(this.url);
        return await data.json() ?? [];
    }

    async getResumeById(id: number): Promise<Resume | undefined> {
        const data = await fetch(`${this.url}/${id}`);
        return data.json() ?? {};
    }
}
