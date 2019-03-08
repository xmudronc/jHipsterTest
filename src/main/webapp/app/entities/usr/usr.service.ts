import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUsr } from 'app/shared/model/usr.model';

type EntityResponseType = HttpResponse<IUsr>;
type EntityArrayResponseType = HttpResponse<IUsr[]>;

@Injectable({ providedIn: 'root' })
export class UsrService {
    private resourceUrl = SERVER_API_URL + 'api/usrs';

    constructor(private http: HttpClient) {}

    create(usr: IUsr): Observable<EntityResponseType> {
        return this.http.post<IUsr>(this.resourceUrl, usr, { observe: 'response' });
    }

    update(usr: IUsr): Observable<EntityResponseType> {
        return this.http.put<IUsr>(this.resourceUrl, usr, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IUsr>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUsr[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
