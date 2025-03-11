import {Injectable} from '@angular/core';
import {Resume} from "../resume";

@Injectable({
    providedIn: 'root'
})
export class ResumeService {
    url = 'http://localhost:3000/resumes';

    constructor() {
    }

    async getAllResumes(): Promise<Resume[]> {
        const data = await fetch(this.url);
        return data.json() ?? [];
    }

    async getResumeById(id: number): Promise<Resume | undefined> {
const data = await fetch(`${this.url}/${id}`);
    return data.json() ?? {};}
}
