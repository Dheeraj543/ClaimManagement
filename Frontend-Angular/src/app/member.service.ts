import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private httpClient: HttpClient,
    private cookieService: CookieService
    ) { }
  msg: string = '';
  loggedIn = false;
  setToken(tkn: string) { this.msg = tkn }
  getToket() { return this.msg }
  setLog(log: boolean) { this.loggedIn = log }
  getLog() { return this.loggedIn }
  public generateToken(request: any) {
    let url = "http://localhost:8400/auth/authenticate";
    return this.httpClient.post<string>(url, request, { responseType: 'text' as 'json' });
  }

  public viewBills(token: any, memberId: number, policyId: number) {
    let tokenStr = 'Bearer ' + token;
    let url = `http://localhost:8100/viewbills/${memberId}/${policyId}`;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    console.log(headers);

    return this.httpClient.get<any>(url, { headers });
  }

  public getClaimStatus(token: any, memberId: number, policyId: number, claimId: number) {
    let tokenStr = 'Bearer ' + token;
    let url = `http://localhost:8100/getclaimstatus/${memberId}/${policyId}/${claimId}`;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    console.log(token);

    return this.httpClient.get<any>(url, { headers });
  }

  public submitClaim(token: any, memberId: number, policyId: number, claim: any) {
    const token1=this.cookieService.get("token")
    console.log("this->",token);
    let tokenStr = 'Bearer ' + token1;
    console.log(claim);
    let url = `http://localhost:8100/submitclaim/${memberId}/${policyId}/${claim.amountClaimed}`;
    console.log("token",tokenStr);
    console.log(url);
    
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    // const header = new Headers({
    //   'Content-Type': 'application/json',
    //   'Authorization': `Bearer ${token}`
    // })
  //   var reqHeader = new HttpHeaders({ 
  //     'Authorization': 'Bearer ' + token
  //  });
    // this.httpHeader.set('Authorization', tokenStr);
    // let header=this.httpHeader;
    console.log(headers.has('Authorization'));
    console.log(headers);
    return this.httpClient.post<string>(url, { headers });
  }
}