package pages;
public class AllPages {

    private CLContactListPage contactListPage;
    private CLAddContactPage addContactPage;

    public CLContactListPage getContactListPage() {
        if (contactListPage == null) {
            contactListPage = new CLContactListPage();
        }
        return contactListPage;
    }

    public CLAddContactPage getAddContactPage() {
        if (addContactPage==null){
            addContactPage = new CLAddContactPage();
        }
        return addContactPage;
    }
}
