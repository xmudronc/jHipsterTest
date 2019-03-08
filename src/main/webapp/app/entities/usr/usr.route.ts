import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Usr } from 'app/shared/model/usr.model';
import { UsrService } from './usr.service';
import { UsrComponent } from './usr.component';
import { UsrDetailComponent } from './usr-detail.component';
import { UsrUpdateComponent } from './usr-update.component';
import { UsrDeletePopupComponent } from './usr-delete-dialog.component';
import { IUsr } from 'app/shared/model/usr.model';

@Injectable({ providedIn: 'root' })
export class UsrResolve implements Resolve<IUsr> {
    constructor(private service: UsrService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((usr: HttpResponse<Usr>) => usr.body);
        }
        return Observable.of(new Usr());
    }
}

export const usrRoute: Routes = [
    {
        path: 'usr',
        component: UsrComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Usrs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'usr/:id/view',
        component: UsrDetailComponent,
        resolve: {
            usr: UsrResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Usrs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'usr/new',
        component: UsrUpdateComponent,
        resolve: {
            usr: UsrResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Usrs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'usr/:id/edit',
        component: UsrUpdateComponent,
        resolve: {
            usr: UsrResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Usrs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const usrPopupRoute: Routes = [
    {
        path: 'usr/:id/delete',
        component: UsrDeletePopupComponent,
        resolve: {
            usr: UsrResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Usrs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
