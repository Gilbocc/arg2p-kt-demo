package org.example

val testTheory : String = """
        art1a: evaluate(Ev), hasAgent(Ev,X), licensee(X), hasTheme(Ev,P), product(P) => o(-evaluate(Ev)).
        art1b: evaluate(Ev), hasAgent(Ev,X), licensee(X), hasTheme(Ev,P), product(P), isLicenceOf(L,P), licence(L), hasTheme(Eg,L), hasAgent(Eg,Y), licensor(Y), grant(Eg), rexist(Eg), hasReceiver(Eg,X) => p(evaluate(Ev)).
        sup(art1b, art1a).
        
        art2aPart1: evaluate(Ev), rexist(Ev), hasResult(Ev,R), result(R), publish(Ep), hasAgent(Ep,X), licensee(X), hasTheme(Ep,R) => condition_2(Ep,X,R).
        art2aPart2: condition_2(Ep,X,R) => o(-publish(Ep)).
        
        art2b: approve(Ea), rexist(Ea), hasTheme(Ea,Ep), hasAgent(Ea,Y), licensor(Y), publish(Ep), hasAgent(Ep,X), licensee(X), hasTheme(Ep,R), result(R), hasResult(Ev,R), evaluate(Ev), rexist(Ev) => p(publish(Ep)).
        sup(art2b, art2aPart2).
        
        art2cPart1: condition_2(Ep,X,R), o(-publish(Ep)), rexist(Ep) => o(remove(ca(Ep,X,R))).
        art2cPart2: condition_2(Ep,X,R), o(-publish(Ep)), rexist(Ep) => remove(ca(Ep,X,R)).
        art2cPart3: condition_2(Ep,X,R), o(-publish(Ep)), rexist(Ep) => hasTheme(ca(Ep,X,R),R).
        art2cPart4: condition_2(Ep,X,R), o(-publish(Ep)), rexist(Ep) => hasAgent(ca(Ep,X,R),X).
        art2e: condition_2(Ep,X,R), o(-publish(Ep)), rexist(Ep) => compensate(ca(Ep,X,R),Ep).
        
        art3a: publish(Ep), hasAgent(Ep,X), licensee(X), hasTheme(Ep,C), comment(C), isCommentOf(C,Ev), evaluate(Ev), rexist(Ev) => o(-publish(Ep)).
        art3b: publish(Ep), hasAgent(Ep,X), licensee(X), hasTheme(Ep,C), comment(C), isCommentOf(C,Ev), evaluate(Ev), rexist(Ev), hasResult(Ev,R), hasTheme(Epr,R), hasAgent(Epr,X), publish(Epr), p(publish(Epr)) => p(publish(Ep)).
        sup(art3b, art3a).
        
        art4a: publish(Ep), hasAgent(Ep,X), licensee(X), hasTheme(Ep,R), result(R), hasResult(Ev,R), evaluate(Ev), rexist(Ev), hasTheme(Ec,Ev), commission(Ec), rexist(Ec) => o(publish(Ep)).
        sup(art4a, art2aPart2).
        
        ccRuleComp1: remove(ca(Ep,X,R)), hasTheme(ca(Ep,X,R),R), hasAgent(ca(Ep,X,R),X), rexist(Er), remove(Er), hasTheme(Er,R), hasAgent(Er,X) => rexist(ca(Ep,X,R)).
        ccRuleComp2: compensate(Y,X), rexist(Y) => compensated(X).
        
        ccRuleEp1: o(-publish(Ep)), rexist(Ep), ~(compensated(Ep)) => referTo(viol(Ep),Ep).
        ccRuleEp2: o(publish(Ep)), ~(rexist(Ep)) => referTo(viol(Ep),Ep).
        ccRuleEr: o(remove(Er)) => referTo(viol(Er),Er).
        
        ruleViolation: referTo(viol(X),X) => violation(viol(X)).
        
        f1 :-> licensee(x_0).
        f2 :-> licensor(y_0).
        f3 :-> product(p_0).
        f4 :-> result(r_0).
        f5 :-> licence(l_0).
        f6 :-> isLicenceOf(l_0,p_0).
        f7 :-> comment(c_0).
        f8 :-> approve(ea_0).
        f9 :-> hasAgent(ea_0,y_0).
        f10 :-> hasTheme(ea_0,epr_0).
        f11 :-> commission(ec_0).
        f12 :-> hasTheme(ec_0,ev_0).
        f13 :-> evaluate(ev_0).
        f14 :-> hasAgent(ev_0,x_0).
        f15 :-> hasTheme(ev_0,p_0).
        f16 :-> hasResult(ev_0,r_0).
        f17 :-> grant(eg_0).
        f18 :-> hasAgent(eg_0,y_0).
        f19 :-> hasReceiver(eg_0,x_0).
        f20 :-> hasTheme(eg_0,l_0).
        f21 :-> publish(epc_0).
        f22 :-> hasAgent(epc_0,x_0).
        f23 :-> hasTheme(epc_0,c_0).
        f24 :-> isCommentOf(c_0,ev_0).
        f25 :-> publish(epr_0).
        f26 :-> hasAgent(epr_0,x_0).
        f27 :-> hasTheme(epr_0,r_0).
        f28 :-> remove(er_0).
        f29 :-> hasAgent(er_0,x_0).
        f30 :-> hasTheme(er_0,r_0).
        f31 :-> rexist(ev_0).
        f32 :-> rexist(eg_0).
        f33 :-> rexist(epr_0).
        f34 :-> licensee(x_1).
        f35 :-> licensor(y_1).
        f36 :-> product(p_1).
        f37 :-> result(r_1).
        f38 :-> licence(l_1).
        f39 :-> isLicenceOf(l_1,p_1).
        f40 :-> comment(c_1).
        f41 :-> approve(ea_1).
        f42 :-> hasAgent(ea_1,y_1).
        f43 :-> hasTheme(ea_1,epr_1).
        f44 :-> commission(ec_1).
        f45 :-> hasTheme(ec_1,ev_1).
        f46 :-> evaluate(ev_1).
        f47 :-> hasAgent(ev_1,x_1).
        f48 :-> hasTheme(ev_1,p_1).
        f49 :-> hasResult(ev_1,r_1).
        f50 :-> grant(eg_1).
        f51 :-> hasAgent(eg_1,y_1).
        f52 :-> hasReceiver(eg_1,x_1).
        f53 :-> hasTheme(eg_1,l_1).
        f54 :-> publish(epc_1).
        f55 :-> hasAgent(epc_1,x_1).
        f56 :-> hasTheme(epc_1,c_1).
        f57 :-> isCommentOf(c_1,ev_1).
        f58 :-> publish(epr_1).
        f59 :-> hasAgent(epr_1,x_1).
        f60 :-> hasTheme(epr_1,r_1).
        f61 :-> remove(er_1).
        f62 :-> hasAgent(er_1,x_1).
        f63 :-> hasTheme(er_1,r_1).
        f64 :-> rexist(ea_1).
        f65 :-> rexist(ec_1).
        f66 :-> rexist(ev_1).
        f67 :-> rexist(eg_1).
        f68 :-> rexist(epc_1).
        f69 :-> rexist(epr_1).
        f70 :-> rexist(er_1).
        f71 :-> licensee(x_2).
        f72 :-> licensor(y_2).
        f73 :-> product(p_2).
        f74 :-> result(r_2).
        f75 :-> licence(l_2).
        f76 :-> isLicenceOf(l_2,p_2).
        f77 :-> comment(c_2).
        f78 :-> approve(ea_2).
        f79 :-> hasAgent(ea_2,y_2).
        f80 :-> hasTheme(ea_2,epr_2).
        f81 :-> commission(ec_2).
        f82 :-> hasTheme(ec_2,ev_2).
        f83 :-> evaluate(ev_2).
        f84 :-> hasAgent(ev_2,x_2).
        f85 :-> hasTheme(ev_2,p_2).
        f86 :-> hasResult(ev_2,r_2).
        f87 :-> grant(eg_2).
        f88 :-> hasAgent(eg_2,y_2).
        f89 :-> hasReceiver(eg_2,x_2).
        f90 :-> hasTheme(eg_2,l_2).
        f91 :-> publish(epc_2).
        f92 :-> hasAgent(epc_2,x_2).
        f93 :-> hasTheme(epc_2,c_2).
        f94 :-> isCommentOf(c_2,ev_2).
        f95 :-> publish(epr_2).
        f96 :-> hasAgent(epr_2,x_2).
        f97 :-> hasTheme(epr_2,r_2).
        f98 :-> remove(er_2).
        f99 :-> hasAgent(er_2,x_2).
        f100 :-> hasTheme(er_2,r_2).
        f101 :-> rexist(ec_2).
        f102 :-> rexist(eg_2).
        f103 :-> rexist(epr_2).
        f104 :-> rexist(er_2).
        f105 :-> licensee(x_3).
        f106 :-> licensor(y_3).
        f107 :-> product(p_3).
        f108 :-> result(r_3).
        f109 :-> licence(l_3).
        f110 :-> isLicenceOf(l_3,p_3).
        f111 :-> comment(c_3).
        f112 :-> approve(ea_3).
        f113 :-> hasAgent(ea_3,y_3).
        f114 :-> hasTheme(ea_3,epr_3).
        f115 :-> commission(ec_3).
        f116 :-> hasTheme(ec_3,ev_3).
        f117 :-> evaluate(ev_3).
        f118 :-> hasAgent(ev_3,x_3).
        f119 :-> hasTheme(ev_3,p_3).
        f120 :-> hasResult(ev_3,r_3).
        f121 :-> grant(eg_3).
        f122 :-> hasAgent(eg_3,y_3).
        f123 :-> hasReceiver(eg_3,x_3).
        f124 :-> hasTheme(eg_3,l_3).
        f125 :-> publish(epc_3).
        f126 :-> hasAgent(epc_3,x_3).
        f127 :-> hasTheme(epc_3,c_3).
        f128 :-> isCommentOf(c_3,ev_3).
        f129 :-> publish(epr_3).
        f130 :-> hasAgent(epr_3,x_3).
        f131 :-> hasTheme(epr_3,r_3).
        f132 :-> remove(er_3).
        f133 :-> hasAgent(er_3,x_3).
        f134 :-> hasTheme(er_3,r_3).
        f135 :-> rexist(ea_3).
        f136 :-> rexist(er_3).
        f137 :-> licensee(x_4).
        f138 :-> licensor(y_4).
        f139 :-> product(p_4).
        f140 :-> result(r_4).
        f141 :-> licence(l_4).
        f142 :-> isLicenceOf(l_4,p_4).
        f143 :-> comment(c_4).
        f144 :-> approve(ea_4).
        f145 :-> hasAgent(ea_4,y_4).
        f146 :-> hasTheme(ea_4,epr_4).
        f147 :-> commission(ec_4).
        f148 :-> hasTheme(ec_4,ev_4).
        f149 :-> evaluate(ev_4).
        f150 :-> hasAgent(ev_4,x_4).
        f151 :-> hasTheme(ev_4,p_4).
        f152 :-> hasResult(ev_4,r_4).
        f153 :-> grant(eg_4).
        f154 :-> hasAgent(eg_4,y_4).
        f155 :-> hasReceiver(eg_4,x_4).
        f156 :-> hasTheme(eg_4,l_4).
        f157 :-> publish(epc_4).
        f158 :-> hasAgent(epc_4,x_4).
        f159 :-> hasTheme(epc_4,c_4).
        f160 :-> isCommentOf(c_4,ev_4).
        f161 :-> publish(epr_4).
        f162 :-> hasAgent(epr_4,x_4).
        f163 :-> hasTheme(epr_4,r_4).
        f164 :-> remove(er_4).
        f165 :-> hasAgent(er_4,x_4).
        f166 :-> hasTheme(er_4,r_4).
        f167 :-> rexist(ea_4).
        f168 :-> rexist(ev_4).
        f169 :-> rexist(er_4).
        f170 :-> licensee(x_5).
        f171 :-> licensor(y_5).
        f172 :-> product(p_5).
        f173 :-> result(r_5).
        f174 :-> licence(l_5).
        f175 :-> isLicenceOf(l_5,p_5).
        f176 :-> comment(c_5).
        f177 :-> approve(ea_5).
        f178 :-> hasAgent(ea_5,y_5).
        f179 :-> hasTheme(ea_5,epr_5).
        f180 :-> commission(ec_5).
        f181 :-> hasTheme(ec_5,ev_5).
        f182 :-> evaluate(ev_5).
        f183 :-> hasAgent(ev_5,x_5).
        f184 :-> hasTheme(ev_5,p_5).
        f185 :-> hasResult(ev_5,r_5).
        f186 :-> grant(eg_5).
        f187 :-> hasAgent(eg_5,y_5).
        f188 :-> hasReceiver(eg_5,x_5).
        f189 :-> hasTheme(eg_5,l_5).
        f190 :-> publish(epc_5).
        f191 :-> hasAgent(epc_5,x_5).
        f192 :-> hasTheme(epc_5,c_5).
        f193 :-> isCommentOf(c_5,ev_5).
        f194 :-> publish(epr_5).
        f195 :-> hasAgent(epr_5,x_5).
        f196 :-> hasTheme(epr_5,r_5).
        f197 :-> remove(er_5).
        f198 :-> hasAgent(er_5,x_5).
        f199 :-> hasTheme(er_5,r_5).
        f200 :-> rexist(ec_5).
        f201 :-> rexist(ev_5).
        f202 :-> rexist(eg_5).
        f203 :-> rexist(epc_5).
        f204 :-> rexist(epr_5).
        f205 :-> licensee(x_6).
        f206 :-> licensor(y_6).
        f207 :-> product(p_6).
        f208 :-> result(r_6).
        f209 :-> licence(l_6).
        f210 :-> isLicenceOf(l_6,p_6).
        f211 :-> comment(c_6).
        f212 :-> approve(ea_6).
        f213 :-> hasAgent(ea_6,y_6).
        f214 :-> hasTheme(ea_6,epr_6).
        f215 :-> commission(ec_6).
        f216 :-> hasTheme(ec_6,ev_6).
        f217 :-> evaluate(ev_6).
        f218 :-> hasAgent(ev_6,x_6).
        f219 :-> hasTheme(ev_6,p_6).
        f220 :-> hasResult(ev_6,r_6).
        f221 :-> grant(eg_6).
        f222 :-> hasAgent(eg_6,y_6).
        f223 :-> hasReceiver(eg_6,x_6).
        f224 :-> hasTheme(eg_6,l_6).
        f225 :-> publish(epc_6).
        f226 :-> hasAgent(epc_6,x_6).
        f227 :-> hasTheme(epc_6,c_6).
        f228 :-> isCommentOf(c_6,ev_6).
        f229 :-> publish(epr_6).
        f230 :-> hasAgent(epr_6,x_6).
        f231 :-> hasTheme(epr_6,r_6).
        f232 :-> remove(er_6).
        f233 :-> hasAgent(er_6,x_6).
        f234 :-> hasTheme(er_6,r_6).
        f235 :-> rexist(ea_6).
        f236 :-> rexist(ec_6).
        f237 :-> rexist(epc_6).
        f238 :-> rexist(er_6).
        f239 :-> licensee(x_7).
        f240 :-> licensor(y_7).
        f241 :-> product(p_7).
        f242 :-> result(r_7).
        f243 :-> licence(l_7).
        f244 :-> isLicenceOf(l_7,p_7).
        f245 :-> comment(c_7).
        f246 :-> approve(ea_7).
        f247 :-> hasAgent(ea_7,y_7).
        f248 :-> hasTheme(ea_7,epr_7).
        f249 :-> commission(ec_7).
        f250 :-> hasTheme(ec_7,ev_7).
        f251 :-> evaluate(ev_7).
        f252 :-> hasAgent(ev_7,x_7).
        f253 :-> hasTheme(ev_7,p_7).
        f254 :-> hasResult(ev_7,r_7).
        f255 :-> grant(eg_7).
        f256 :-> hasAgent(eg_7,y_7).
        f257 :-> hasReceiver(eg_7,x_7).
        f258 :-> hasTheme(eg_7,l_7).
        f259 :-> publish(epc_7).
        f260 :-> hasAgent(epc_7,x_7).
        f261 :-> hasTheme(epc_7,c_7).
        f262 :-> isCommentOf(c_7,ev_7).
        f263 :-> publish(epr_7).
        f264 :-> hasAgent(epr_7,x_7).
        f265 :-> hasTheme(epr_7,r_7).
        f266 :-> remove(er_7).
        f267 :-> hasAgent(er_7,x_7).
        f268 :-> hasTheme(er_7,r_7).
        f269 :-> rexist(ec_7).
        f270 :-> rexist(ev_7).
        f271 :-> rexist(eg_7).
        f272 :-> rexist(epc_7).
        f273 :-> rexist(epr_7).
        f274 :-> rexist(er_7).
        f275 :-> licensee(x_8).
        f276 :-> licensor(y_8).
        f277 :-> product(p_8).
        f278 :-> result(r_8).
        f279 :-> licence(l_8).
        f280 :-> isLicenceOf(l_8,p_8).
        f281 :-> comment(c_8).
        f282 :-> approve(ea_8).
        f283 :-> hasAgent(ea_8,y_8).
        f284 :-> hasTheme(ea_8,epr_8).
        f285 :-> commission(ec_8).
        f286 :-> hasTheme(ec_8,ev_8).
        f287 :-> evaluate(ev_8).
        f288 :-> hasAgent(ev_8,x_8).
        f289 :-> hasTheme(ev_8,p_8).
        f290 :-> hasResult(ev_8,r_8).
        f291 :-> grant(eg_8).
        f292 :-> hasAgent(eg_8,y_8).
        f293 :-> hasReceiver(eg_8,x_8).
        f294 :-> hasTheme(eg_8,l_8).
        f295 :-> publish(epc_8).
        f296 :-> hasAgent(epc_8,x_8).
        f297 :-> hasTheme(epc_8,c_8).
        f298 :-> isCommentOf(c_8,ev_8).
        f299 :-> publish(epr_8).
        f300 :-> hasAgent(epr_8,x_8).
        f301 :-> hasTheme(epr_8,r_8).
        f302 :-> remove(er_8).
        f303 :-> hasAgent(er_8,x_8).
        f304 :-> hasTheme(er_8,r_8).
        f305 :-> rexist(epc_8).
        f306 :-> rexist(epr_8).
        f307 :-> licensee(x_9).
        f308 :-> licensor(y_9).
        f309 :-> product(p_9).
        f310 :-> result(r_9).
        f311 :-> licence(l_9).
        f312 :-> isLicenceOf(l_9,p_9).
        f313 :-> comment(c_9).
        f314 :-> approve(ea_9).
        f315 :-> hasAgent(ea_9,y_9).
        f316 :-> hasTheme(ea_9,epr_9).
        f317 :-> commission(ec_9).
        f318 :-> hasTheme(ec_9,ev_9).
        f319 :-> evaluate(ev_9).
        f320 :-> hasAgent(ev_9,x_9).
        f321 :-> hasTheme(ev_9,p_9).
        f322 :-> hasResult(ev_9,r_9).
        f323 :-> grant(eg_9).
        f324 :-> hasAgent(eg_9,y_9).
        f325 :-> hasReceiver(eg_9,x_9).
        f326 :-> hasTheme(eg_9,l_9).
        f327 :-> publish(epc_9).
        f328 :-> hasAgent(epc_9,x_9).
        f329 :-> hasTheme(epc_9,c_9).
        f330 :-> isCommentOf(c_9,ev_9).
        f331 :-> publish(epr_9).
        f332 :-> hasAgent(epr_9,x_9).
        f333 :-> hasTheme(epr_9,r_9).
        f334 :-> remove(er_9).
        f335 :-> hasAgent(er_9,x_9).
        f336 :-> hasTheme(er_9,r_9).
        f337 :-> rexist(epr_9).
        f338 :-> rexist(er_9).

""".trimIndent()