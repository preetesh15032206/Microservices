/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { Database, Server, Shield, Code2, Terminal } from 'lucide-react';

export default function App() {
  return (
    <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-center p-6 text-gray-800">
      <div className="max-w-3xl bg-white p-8 rounded-xl shadow-xl border border-gray-100 w-full">
        <div className="w-16 h-16 bg-blue-100 text-blue-600 rounded-full flex items-center justify-center mx-auto mb-6">
          <Code2 className="w-8 h-8" />
        </div>
        
        <h1 className="text-3xl font-bold text-gray-900 mb-2 text-center">Employee Management System</h1>
        <p className="text-gray-600 mb-8 text-center text-lg">
          A Full-Stack Java Application built for Enterprise Operations
        </p>
        
        <div className="grid md:grid-cols-2 gap-4 mb-8">
          <div className="bg-gray-50 p-4 rounded-lg border border-gray-200">
            <h3 className="font-bold mb-2 flex items-center text-gray-800">
              <Server className="w-5 h-5 me-2 text-blue-600" /> Backend
            </h3>
            <ul className="text-sm text-gray-600 space-y-1">
              <li>• Java 17 & Spring Boot 3</li>
              <li>• Spring Security (Role-based Auth)</li>
              <li>• RESTful APIs</li>
              <li>• Maven & JUnit/Mockito</li>
            </ul>
          </div>
          <div className="bg-gray-50 p-4 rounded-lg border border-gray-200">
            <h3 className="font-bold mb-2 flex items-center text-gray-800">
              <Database className="w-5 h-5 me-2 text-green-600" /> Data & UI
            </h3>
            <ul className="text-sm text-gray-600 space-y-1">
              <li>• MySQL & Spring Data JPA</li>
              <li>• Hibernate ORM</li>
              <li>• Thymeleaf Template Engine</li>
              <li>• Bootstrap 5 Responsive UI</li>
            </ul>
          </div>
        </div>
        
        <div className="bg-blue-50 border border-blue-100 text-blue-900 p-5 rounded-lg mb-6">
          <h3 className="font-bold mb-3 flex items-center text-blue-800">
            <Shield className="w-5 h-5 me-2" /> Key Features
          </h3>
          <ul className="list-disc list-inside space-y-2 text-sm text-blue-800 mb-4 ml-2">
            <li>Secure session management with BCrypt password hashing.</li>
            <li>Admin & User role separation (RBAC) ensuring data protection.</li>
            <li>Complete CRUD operations for managing enterprise employee records.</li>
            <li>Fully responsive and accessible frontend interface.</li>
            <li>Architected using the strict MVC design pattern.</li>
          </ul>
        </div>
        
        <div className="bg-gray-800 text-gray-100 p-5 rounded-lg text-sm mb-6">
          <h4 className="font-bold mb-2 flex items-center text-gray-300">
            <Terminal className="w-4 h-4 me-2" /> Run from Source:
          </h4>
          <pre className="bg-gray-900 p-3 rounded font-mono text-green-400 overflow-x-auto text-xs">
            $ cd java-backend/<br/>
            $ mvn clean install<br/>
            $ mvn spring-boot:run
          </pre>
        </div>
      </div>
    </div>
  );
}
