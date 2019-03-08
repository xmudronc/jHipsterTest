import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { UsrComponentsPage, UsrUpdatePage } from './usr.page-object';

describe('Usr e2e test', () => {
    let navBarPage: NavBarPage;
    let usrUpdatePage: UsrUpdatePage;
    let usrComponentsPage: UsrComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Usrs', () => {
        navBarPage.goToEntity('usr');
        usrComponentsPage = new UsrComponentsPage();
        expect(usrComponentsPage.getTitle()).toMatch(/Usrs/);
    });

    it('should load create Usr page', () => {
        usrComponentsPage.clickOnCreateButton();
        usrUpdatePage = new UsrUpdatePage();
        expect(usrUpdatePage.getPageTitle()).toMatch(/Create or edit a Usr/);
        usrUpdatePage.cancel();
    });

    it('should create and save Usrs', () => {
        usrComponentsPage.clickOnCreateButton();
        usrUpdatePage.setUserIDInput('userID');
        expect(usrUpdatePage.getUserIDInput()).toMatch('userID');
        usrUpdatePage.setPasswordInput('password');
        expect(usrUpdatePage.getPasswordInput()).toMatch('password');
        usrUpdatePage.save();
        expect(usrUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
