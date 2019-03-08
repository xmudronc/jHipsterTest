import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IUsr } from 'app/shared/model/usr.model';
import { UsrService } from './usr.service';

@Component({
    selector: 'jhi-usr-update',
    templateUrl: './usr-update.component.html'
})
export class UsrUpdateComponent implements OnInit {
    private _usr: IUsr;
    isSaving: boolean;

    constructor(private usrService: UsrService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ usr }) => {
            this.usr = usr;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.usr.id !== undefined) {
            this.subscribeToSaveResponse(this.usrService.update(this.usr));
        } else {
            this.subscribeToSaveResponse(this.usrService.create(this.usr));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUsr>>) {
        result.subscribe((res: HttpResponse<IUsr>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get usr() {
        return this._usr;
    }

    set usr(usr: IUsr) {
        this._usr = usr;
    }
}
