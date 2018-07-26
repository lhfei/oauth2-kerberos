import {ClaritySeedAppHome} from './app.po';

fdescribe('galaxy-app', function () {

  let expectedMsg: string = 'Galaxy Platform.';

  let page: ClaritySeedAppHome;

  beforeEach(() => {
    page = new ClaritySeedAppHome();
  });

  it('should display: ' + expectedMsg, () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual(expectedMsg)
  });
});
